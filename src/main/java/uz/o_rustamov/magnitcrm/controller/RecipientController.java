package uz.o_rustamov.magnitcrm.controller;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import uz.o_rustamov.magnitcrm.ApiResponse;
import uz.o_rustamov.magnitcrm.payload.RecipientDto;
import uz.o_rustamov.magnitcrm.service.RecipientServiceImpl;

import javax.validation.Valid;

@Transactional
@RestController
@RequestMapping("/api/recipient")
public class RecipientController {

    RecipientServiceImpl recipientService;

    public RecipientController(RecipientServiceImpl recipientService) {
        this.recipientService = recipientService;
    }

    @PreAuthorize(value = "hasAuthority('VIEW_RECIPIENTS')")
    @GetMapping
    public HttpEntity<ApiResponse> getRecipients() {
        return recipientService.getAllRecipients();
    }


    @PreAuthorize(value = "hasAuthority('VIEW_RECIPIENTS')")
    @GetMapping("/{id}")
    public HttpEntity<ApiResponse> getRecipientById(@PathVariable Long id) {
        return recipientService.getRecipientById(id);
    }


    @PreAuthorize(value = "hasAuthority('ADD_RECIPIENT')")
    @PostMapping
    public HttpEntity<ApiResponse> addRecipient(@Valid @RequestBody RecipientDto dto) {
        return recipientService.addRecipient(dto);
    }

    @PreAuthorize(value = "hasAuthority('EDIT_RECIPIENT')")
    @PutMapping("/{id}")
    public HttpEntity<ApiResponse> editRecipient(@Valid @RequestBody RecipientDto dto, @PathVariable Long id) {
        return recipientService.editRecipient(id, dto);
    }


    @PreAuthorize(value = "hasAuthority('DELETE_RECIPIENT')")
    @DeleteMapping("/{id}")
    public HttpEntity<ApiResponse> deleteRecipient(@PathVariable Long id) {
        return recipientService.deleteRecipient(id);
    }
}
