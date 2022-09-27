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

    @PreAuthorize("hasAuthority('VIEW_MY_OUTPUTS')")
    @GetMapping("/my-outputs")
    public HttpEntity<ApiResponse> getMyOutputs(@CurrentUser User user, @RequestParam int page, @RequestParam int size) {
        return outputService.getMyOutputs(user, page, size);
    }

    @PreAuthorize("hasAuthority('VIEW_MY_OUTPUTS')")
    @GetMapping("/my-outputs-and-date")
    public HttpEntity<ApiResponse> getMyOutputsAndDate(@CurrentUser User user, @RequestParam String from, @RequestParam String to, @RequestParam int page, int size) {
        return outputService.getMyOutputsAndDate(user, from, to, page, size);
    }

    @PreAuthorize("hasAuthority('VIEW_OUTPUTS')")
    @GetMapping("/recipient-and-date/{recipientId}")
    public HttpEntity<ApiResponse> getOutputByRecipientId(@PathVariable long recipientId,
                                                          @RequestParam String from, @RequestParam String to) {
        return outputService.getOutputByDateAndRecipientId(from, to, recipientId);
    }

    @PreAuthorize("hasAuthority('ADD_OUTPUT')")
    @PostMapping
    public HttpEntity<ApiResponse> addOutput(@CurrentUser User user, @Valid @RequestBody OutputDto dto) {
        return outputService.addOutput(user, dto);
    }

    @PreAuthorize("hasAuthority('DELETE_OUTPUT')")
    @DeleteMapping("/{outputId}")
    public HttpEntity<ApiResponse> deleteOutput(@PathVariable Long outputId) {
        return outputService.deleteOutput(outputId);
    }

    @PostMapping("/confirm")
    public HttpEntity<ApiResponse> confirmOutput(@CurrentUser User user, @RequestParam Long id) {
        return outputService.confirmOutput(user, id);
    }

}
