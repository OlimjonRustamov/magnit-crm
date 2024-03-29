package uz.o_rustamov.magnitcrm.service;

import com.google.firebase.messaging.FirebaseMessagingException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.o_rustamov.magnitcrm.ApiResponse;
import uz.o_rustamov.magnitcrm.abs_interface.ProductService;
import uz.o_rustamov.magnitcrm.entity.Product;
import uz.o_rustamov.magnitcrm.entity.Supplier;
import uz.o_rustamov.magnitcrm.entity.User;
import uz.o_rustamov.magnitcrm.fcm.FirebaseMessagingService;
import uz.o_rustamov.magnitcrm.payload.ProductDto;
import uz.o_rustamov.magnitcrm.repository.ProductRepository;
import uz.o_rustamov.magnitcrm.repository.SupplierRepository;

import java.util.Optional;

import static uz.o_rustamov.magnitcrm.Constants.*;

@Service
public class ProductServiceImpl implements ProductService {

    FirebaseMessagingService fcm;
    SupplierRepository supplierRepository;

    ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository, SupplierRepository supplierRepository, FirebaseMessagingService fcm) {
        this.productRepository = productRepository;
        this.supplierRepository = supplierRepository;
        this.fcm = fcm;
    }

    @Override
    public HttpEntity<ApiResponse> getAllProducts() {
        return ResponseEntity.ok(new ApiResponse(
                null, 200, true, productRepository.findAll()));
    }

    @Override
    public HttpEntity<ApiResponse> getProductById(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent())
            return ResponseEntity.ok(new ApiResponse(null, 200, true, optionalProduct.get()));
        return NOT_FOUND;
    }

    @Override
    public HttpEntity<ApiResponse> editProduct(User user,Long id, ProductDto dto) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            product.setName(dto.getName());
            product.setCostForClient(dto.getCostForClient());
            if (dto.getBalance() != product.getBalance()) {
                if (product.getBalance() != dto.getBalance()) {
                    try {
                        fcm.sendNotiToOtherManagers("Mahsulot miqdori o'zgardi",
                                product.getName() + " nomli mahsulot miqdori " + dto.getBalance() +
                                        " ga "+user.getFullName()+" tomonidan o'zgartirildi. Oldingi miqdor " + product.getBalance(), user.getFcmToken());
                    } catch (FirebaseMessagingException | IllegalArgumentException e) {
                        return FCM_ERROR;
                    }
                }
            }
            product.setCostForDriver(dto.getCostForDriver());
            product.setBalance(dto.getBalance());
            product.setCountInBox(dto.getCountInBox());
            product.setReceivedCost(dto.getReceivedCost());
            productRepository.save(product);
            return SUCCESS;
        }
        return NOT_FOUND;
    }

    @Override
    public HttpEntity<ApiResponse> addProduct(ProductDto dto) {
        if (productRepository.existsByName(dto.getName()))
            return ALREADY_EXIST;
        Optional<Supplier> optionalSupplier = supplierRepository.findById(dto.getSupplierId());
        if (!optionalSupplier.isPresent())
            return NOT_FOUND;
        Supplier supplier = optionalSupplier.get();
        Product product = new Product();
        product.setBalance(dto.getBalance());
        product.setName(dto.getName());
        product.setCostForClient(dto.getCostForClient());
        product.setCostForDriver(dto.getCostForDriver());
        product.setSupplier(supplier);
        product.setCountInBox(dto.getCountInBox());
        product.setReceivedCost(dto.getReceivedCost());
        productRepository.save(product);
        return SUCCESS;
    }

    @Override
    public HttpEntity<ApiResponse> deleteProduct(Long id) {
        try {
            productRepository.deleteById(id);
            return SUCCESS;
        } catch (
                DataIntegrityViolationException ex) {
            return CONNECTED_WITH_OTHERS_EXCEPTION;
        } catch (Exception e) {
            return NOT_FOUND;
        }
    }
}
