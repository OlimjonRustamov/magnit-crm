package uz.o_rustamov.magnitcrm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.o_rustamov.magnitcrm.ApiResponse;
import uz.o_rustamov.magnitcrm.payload.LoginDto;
import uz.o_rustamov.magnitcrm.service.AuthServiceImpl;

import javax.validation.Valid;

@Transactional
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    AuthServiceImpl authService;

    public AuthController(AuthServiceImpl authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public HttpEntity<ApiResponse> login(@Valid @RequestBody LoginDto dto) {
        return authService.login(dto);
    }

}
