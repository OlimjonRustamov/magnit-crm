package uz.o_rustamov.magnitcrm.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.o_rustamov.magnitcrm.ApiResponse;
import uz.o_rustamov.magnitcrm.abs_interface.SupplierService;
import uz.o_rustamov.magnitcrm.entity.Supplier;
import uz.o_rustamov.magnitcrm.payload.SupplierDto;
import uz.o_rustamov.magnitcrm.repository.SupplierRepository;

import java.util.Optional;

import static uz.o_rustamov.magnitcrm.Constants.*;

@Service
public class SupplierServiceImpl implements SupplierService {

    SupplierRepository supplierRepository;

    public SupplierServiceImpl(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @Override
    public HttpEntity<ApiResponse> getAllSuppliers() {
        return ResponseEntity.ok(new ApiResponse(
                null, 200, true, supplierRepository.findAll()));
    }

    @Override
    public HttpEntity<ApiResponse> getSupplierById(Long id) {
        Optional<Supplier> optionalSupplier = supplierRepository.findById(id);
        if (optionalSupplier.isPresent())
            return ResponseEntity.ok(new ApiResponse(null, 200, true, optionalSupplier.get()));
        return NOT_FOUND;
    }

    @Override
    public HttpEntity<ApiResponse> editSupplier(Long id, SupplierDto dto) {
        Optional<Supplier> optionalSupplier = supplierRepository.findById(id);
        if (optionalSupplier.isPresent()) {
            Supplier supplier = optionalSupplier.get();
            supplier.setName(dto.getName());
            supplierRepository.save(supplier);
            return SUCCESS;
        }
        return NOT_FOUND;
    }

    @Override
    public HttpEntity<ApiResponse> addSupplier(SupplierDto dto) {
        if(supplierRepository.existsByName(dto.getName()))
            return ALREADY_EXIST;
        Supplier supplier = new Supplier();
        supplier.setName(dto.getName());
        supplierRepository.save(supplier);
        return SUCCESS;
    }

    @Override
    public HttpEntity<ApiResponse> deleteSupplier(Long id) {
        try {
            supplierRepository.deleteById(id);
            return SUCCESS;
        } catch (Exception e) {
            return NOT_FOUND;
        }
    }
}
