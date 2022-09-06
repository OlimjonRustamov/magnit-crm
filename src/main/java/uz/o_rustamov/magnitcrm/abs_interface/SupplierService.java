package uz.o_rustamov.magnitcrm.abs_interface;


import org.springframework.http.HttpEntity;
import uz.o_rustamov.magnitcrm.ApiResponse;
import uz.o_rustamov.magnitcrm.payload.SupplierDto;

public interface SupplierService {

    HttpEntity<ApiResponse> getAllSuppliers();

    HttpEntity<ApiResponse> getSupplierById(Long id);

    HttpEntity<ApiResponse> editSupplier(Long id, SupplierDto dto);

    HttpEntity<ApiResponse> addSupplier(SupplierDto dto);

    HttpEntity<ApiResponse> deleteSupplier(Long id);

}
