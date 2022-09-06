package uz.o_rustamov.magnitcrm.controller;

import org.springframework.http.HttpEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.o_rustamov.magnitcrm.ApiResponse;
import uz.o_rustamov.magnitcrm.payload.SupplierDto;
import uz.o_rustamov.magnitcrm.service.SupplierServiceImpl;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/supplier")
public class SupplierController {

    SupplierServiceImpl supplierService;

    public SupplierController(SupplierServiceImpl supplierService) {
        this.supplierService = supplierService;
    }

    @PreAuthorize(value = "hasAuthority('VIEW_SUPPLIERS')")
    @GetMapping
    public HttpEntity<ApiResponse> getSuppliers() {
        return supplierService.getAllSuppliers();
    }


    @PreAuthorize(value = "hasAuthority('VIEW_SUPPLIERS')")
    @GetMapping("/{id}")
    public HttpEntity<ApiResponse> getSupplierById(@PathVariable Long id) {
        return supplierService.getSupplierById(id);
    }


    @PreAuthorize(value = "hasAuthority('ADD_SUPPLIER')")
    @PostMapping
    public HttpEntity<ApiResponse> addSupplier(@Valid @RequestBody SupplierDto dto) {
        return supplierService.addSupplier(dto);
    }

    @PreAuthorize(value = "hasAuthority('EDIT_SUPPLIER')")
    @PutMapping("/{id}")
    public HttpEntity<ApiResponse> editSupplier(@Valid @RequestBody SupplierDto dto, @PathVariable Long id) {
        return supplierService.editSupplier(id, dto);
    }


    @PreAuthorize(value = "hasAuthority('DELETE_SUPPLIER')")
    @DeleteMapping("/{id}")
    public HttpEntity<ApiResponse> deleteSupplier(@PathVariable Long id) {
        return supplierService.deleteSupplier(id);
    }
}
