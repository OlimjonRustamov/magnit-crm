package uz.o_rustamov.magnitcrm.controller;

import org.springframework.http.HttpEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import uz.o_rustamov.magnitcrm.ApiResponse;
import uz.o_rustamov.magnitcrm.annotation.CurrentUser;
import uz.o_rustamov.magnitcrm.entity.User;
import uz.o_rustamov.magnitcrm.payload.OutputDto;
import uz.o_rustamov.magnitcrm.service.OutputServiceImpl;

import javax.validation.Valid;

@Transactional
@RestController
@RequestMapping("/api/output")
public class OutputController {

    OutputServiceImpl outputService;

    public OutputController(OutputServiceImpl outputService) {
        this.outputService = outputService;
    }

    @PreAuthorize("hasAuthority('VIEW_OUTPUTS')")
    @GetMapping
    public HttpEntity<ApiResponse> getAllOutputs() {
        return outputService.getAllOutputs();
    }

    @PreAuthorize("hasAuthority('VIEW_OUTPUTS')")
    @GetMapping("/id/{id}")
    public HttpEntity<ApiResponse> getOutputById(@PathVariable long id) {
        return outputService.getOutputById(id);
    }

    @PreAuthorize("hasAuthority('VIEW_OUTPUTS')")
    @GetMapping("/date")
    public HttpEntity<ApiResponse> getOutputByDate(@RequestParam String from, @RequestParam String to) {
        return outputService.getOutputByDate(from, to);
    }

    @PreAuthorize("hasAuthority('VIEW_OUTPUTS')")
    @GetMapping("/recipient/{recipientId}")
    public HttpEntity<ApiResponse> getOutputByRecipientId(@PathVariable long recipientId) {
        return outputService.getAllByRecipientId(recipientId);
    }

    @PreAuthorize("hasAuthority('ADD_OUTPUT')")
    @PostMapping
    public HttpEntity<ApiResponse> addOutput(@CurrentUser User user, @Valid @RequestBody OutputDto dto) {
        return outputService.addOutput(user, dto);
    }

}
