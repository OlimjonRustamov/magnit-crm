package uz.o_rustamov.magnitcrm.controller;

import org.springframework.http.HttpEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.o_rustamov.magnitcrm.ApiResponse;
import uz.o_rustamov.magnitcrm.annotation.CurrentUser;
import uz.o_rustamov.magnitcrm.entity.User;
import uz.o_rustamov.magnitcrm.payload.InputDto;
import uz.o_rustamov.magnitcrm.service.InputServiceImpl;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/input")
public class InputController {

    InputServiceImpl inputService;

    public InputController(InputServiceImpl inputService) {
        this.inputService = inputService;
    }

    @PreAuthorize("hasAuthority('VIEW_INPUTS')")
    @GetMapping
    public HttpEntity<ApiResponse> getAllInputs() {
        return inputService.getAllInputs();
    }

    @PreAuthorize("hasAuthority('VIEW_INPUTS')")
    @GetMapping("/id/{id}")
    public HttpEntity<ApiResponse> getInputById(@PathVariable long id) {
        return inputService.getInputById(id);
    }

    @PreAuthorize("hasAuthority('VIEW_INPUTS')")
    @GetMapping("/date")
    public HttpEntity<ApiResponse> getInputByDate(@RequestParam String from, @RequestParam String to) {
        return inputService.getInputByDate(from, to);
    }

    @PreAuthorize("hasAuthority('VIEW_INPUTS')")
    @GetMapping("/supplier/{supplierId}")
    public HttpEntity<ApiResponse> getInputBySupplierId(@PathVariable long supplierId) {
        return inputService.getAllBySupplierId(supplierId);
    }

    @PreAuthorize("hasAuthority('ADD_INPUT')")
    @PostMapping
    public HttpEntity<ApiResponse> addInput(@CurrentUser User user, @Valid @RequestBody InputDto dto) {
        return inputService.addInput(user, dto);
    }

    @PreAuthorize("hasAuthority('DELETE_INPUT')")
    @DeleteMapping("{id}")
    public HttpEntity<ApiResponse> deleteInput(@PathVariable long id) {
        return inputService.deleteInput(id);
    }


}
