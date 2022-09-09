package uz.o_rustamov.magnitcrm.abs_interface;

import org.springframework.http.HttpEntity;
import uz.o_rustamov.magnitcrm.ApiResponse;
import uz.o_rustamov.magnitcrm.entity.User;
import uz.o_rustamov.magnitcrm.payload.ProductDto;

public interface ProductService {

    HttpEntity<ApiResponse> getAllProducts();

    HttpEntity<ApiResponse> getProductById(Long id);

    HttpEntity<ApiResponse> editProduct(User user, Long id, ProductDto dto);

    HttpEntity<ApiResponse> addProduct(ProductDto dto);

    HttpEntity<ApiResponse> deleteProduct(Long id);

}
