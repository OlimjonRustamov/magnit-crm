package uz.o_rustamov.magnitcrm.controller;

import org.springframework.http.HttpEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.o_rustamov.magnitcrm.ApiResponse;
import uz.o_rustamov.magnitcrm.payload.ProductDto;
import uz.o_rustamov.magnitcrm.service.ProductServiceImpl;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    ProductServiceImpl productService;

    public ProductController(ProductServiceImpl productService) {
        this.productService = productService;
    }

    @PreAuthorize(value = "hasAuthority('VIEW_PRODUCTS')")
    @GetMapping
    public HttpEntity<ApiResponse> getProducts() {
        return productService.getAllProducts();
    }


    @PreAuthorize(value = "hasAuthority('VIEW_PRODUCTS')")
    @GetMapping("/{id}")
    public HttpEntity<ApiResponse> getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }


    @PreAuthorize(value = "hasAuthority('ADD_PRODUCT')")
    @PostMapping
    public HttpEntity<ApiResponse> addProduct(@Valid @RequestBody ProductDto dto) {
        return productService.addProduct(dto);
    }

    @PreAuthorize(value = "hasAuthority('EDIT_PRODUCT')")
    @PutMapping("/{id}")
    public HttpEntity<ApiResponse> editProduct(@Valid @RequestBody ProductDto dto, @PathVariable Long id) {
        return productService.editProduct(id, dto);
    }


    @PreAuthorize(value = "hasAuthority('DELETE_PRODUCT')")
    @DeleteMapping("/{id}")
    public HttpEntity<ApiResponse> deleteProduct(@PathVariable Long id) {
        return productService.deleteProduct(id);
    }
}