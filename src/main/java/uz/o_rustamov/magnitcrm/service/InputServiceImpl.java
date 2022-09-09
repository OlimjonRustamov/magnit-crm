package uz.o_rustamov.magnitcrm.service;

import com.google.firebase.messaging.FirebaseMessagingException;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.o_rustamov.magnitcrm.ApiResponse;
import uz.o_rustamov.magnitcrm.abs_interface.InputService;
import uz.o_rustamov.magnitcrm.entity.*;
import uz.o_rustamov.magnitcrm.fcm.FirebaseMessagingService;
import uz.o_rustamov.magnitcrm.payload.InputDto;
import uz.o_rustamov.magnitcrm.payload.InputProductDto;
import uz.o_rustamov.magnitcrm.repository.InputProductRepository;
import uz.o_rustamov.magnitcrm.repository.InputRepository;
import uz.o_rustamov.magnitcrm.repository.ProductRepository;
import uz.o_rustamov.magnitcrm.repository.SupplierRepository;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static uz.o_rustamov.magnitcrm.Constants.*;

@Service
public class InputServiceImpl implements InputService {

    FirebaseMessagingService fcm;
    InputProductRepository inputProductRepository;
    ProductRepository productRepository;
    InputRepository inputRepository;
    SupplierRepository supplierRepository;

    public InputServiceImpl(InputRepository inputRepository, InputProductRepository inputProductRepository, SupplierRepository supplierRepository, ProductRepository productRepository, FirebaseMessagingService fcm) {
        this.inputRepository = inputRepository;
        this.inputProductRepository = inputProductRepository;
        this.supplierRepository = supplierRepository;
        this.productRepository = productRepository;
        this.fcm = fcm;
    }

    @Override
    public HttpEntity<ApiResponse> getAllInputs() {
        Map<String, Object> data = new HashMap<String, Object>();

        long sumGivenMoney = inputRepository.sumGivenMoney();
        long sumAllProductCost = inputRepository.sumAllProductsCost();
        long countInputs = inputRepository.count();

        data.put("inputs", inputRepository.findAllDesc());
        data.put("given_money", sumGivenMoney);
        data.put("all_product_cost", sumAllProductCost);
        data.put("difference", sumAllProductCost - sumGivenMoney);
        data.put("count", countInputs);
        return ResponseEntity.ok(new ApiResponse(null, 200, true, data));
    }


    @Override
    public HttpEntity<ApiResponse> getInputById(Long id) {
        Optional<Input> optionalInput = inputRepository.findById(id);
        if (optionalInput.isPresent()) {
            Map<String, Object> data = new HashMap<>();
            data.put("input", optionalInput.get());
            data.put("product_list", inputProductRepository.findAllByInput_Id(id));
            return ResponseEntity.ok(new ApiResponse(null, 200, true, data));
        }
        return NOT_FOUND;
    }

    @Override
    public HttpEntity<ApiResponse> getInputByDate(String fromDate, String toDate) {
        try {
            Date from = new Date(new SimpleDateFormat("dd.MM.yyyy").parse(fromDate).getTime());
            Date to = new Date(new SimpleDateFormat("dd.MM.yyyy").parse(toDate).getTime());
            Map<String, Object> data = new HashMap<String, Object>();

            long sumGivenMoney = inputRepository.sumGivenMoney(from, to);
            long sumAllProductCost = inputRepository.sumAllProductsCost(from, to);
            long countInputs = inputRepository.countByPeriod(from, to);

            data.put("inputs", inputRepository.findAllByPeriod(from, to));
            data.put("given_money", sumGivenMoney);
            data.put("all_product_cost", sumAllProductCost);
            data.put("difference", sumAllProductCost - sumGivenMoney);
            data.put("count", countInputs);
            return ResponseEntity.ok(new ApiResponse(null, 200, true, data));
        } catch (ParseException e) {
            return PARSE_EXCEPTION;
        }
    }

    @Override
    public HttpEntity<ApiResponse> getAllBySupplierId(Long supplierId) {
        Map<String, Object> data = new HashMap<String, Object>();
        long sumGivenMoney = inputRepository.sumGivenMoney(supplierId);
        long sumAllProductCost = inputRepository.sumAllProductsCost(supplierId);
        long countInputs = inputRepository.countBySupplier_Id(supplierId);

        data.put("inputs", inputRepository.findAllBySupplier_Id(supplierId));
        data.put("given_money", sumGivenMoney);
        data.put("all_product_cost", sumAllProductCost);
        data.put("difference", sumAllProductCost - sumGivenMoney);
        data.put("count", countInputs);
        return ResponseEntity.ok(new ApiResponse(null, 200, true, data));
    }

    @Override
    public HttpEntity<ApiResponse> addInput(User user, InputDto dto) {
        Optional<Supplier> optionalSupplier = supplierRepository.findById(dto.getSupplierId());
        if (!optionalSupplier.isPresent())
            return NOT_FOUND;
        Date date;
        try {
            date = new Date(new SimpleDateFormat("dd.MM.yyyy").parse(dto.getDate()).getTime());
        } catch (ParseException e) {
            return PARSE_EXCEPTION;
        }
        Supplier supplier = optionalSupplier.get();
        long costAllProducts = 0L;
        Input input = new Input(date, costAllProducts, supplier, dto.getGivenMoney(), user, dto.getNote());
        input = inputRepository.save(input);
        List<InputProductDto> products = dto.getProductList();
        for (InputProductDto inputProductDto : products) {
            Optional<Product> optionalProduct = productRepository.findById(inputProductDto.getProductId());
            if (!optionalProduct.isPresent()) return PRODUCT_NOT_FOUND_APPLY_TO_DEVELOPER;
            Product product = optionalProduct.get();
            InputProduct inputProduct = new InputProduct(
                    product, inputProductDto.getBoxCount(), input, product.getReceivedCost());
            inputProduct = inputProductRepository.save(inputProduct);
            costAllProducts += (long) inputProductDto.getBoxCount() * product.getReceivedCost() * product.getCountInBox();
            product.setBalance(product.getBalance() + inputProduct.getBoxCount());
            productRepository.save(product);
        }
        input.setAllProductCost(costAllProducts);
        input = inputRepository.save(input);
        try {
            fcm.sendNotiToOtherManagers("Yangi mahsulotlar",
                    supplier.getName() + "'dan yangi mahsulotlar " + user.getFullName() +
                            " tomonidan kiritildi", user.getFcm_token());
        } catch (FirebaseMessagingException e) {
            return FCM_ERROR;
        }
        return SUCCESS;
    }

    @Override
    public HttpEntity<ApiResponse> deleteInput(Long id) {
        Optional<Input> optionalInput = inputRepository.findById(id);
        if (!optionalInput.isPresent()) return NOT_FOUND;
        try {
            inputProductRepository.deleteAllByInput_Id(id);
            inputRepository.deleteById(id);
        } catch (Exception e) {
            return CONNECTED_WITH_OTHERS_EXCEPTION;
        }
        return SUCCESS;
    }
}
