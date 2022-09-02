package uz.o_rustamov.magnitcrm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;
import uz.o_rustamov.magnitcrm.ApiResponse;
import uz.o_rustamov.magnitcrm.annotation.CurrentUser;
import uz.o_rustamov.magnitcrm.entity.User;
import uz.o_rustamov.magnitcrm.payload.ChangePasswordDto;
import uz.o_rustamov.magnitcrm.service.ProfileServiceImpl;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {
    @Autowired
    ProfileServiceImpl profileService;

    @GetMapping("/get-me")
    public HttpEntity<ApiResponse> getMe(@CurrentUser User user) {
        return profileService.getMe(user);
    }

    @PostMapping("/change-password")
    public HttpEntity<ApiResponse> changePassword(@CurrentUser User user, @RequestBody ChangePasswordDto dto) {
        return profileService.changePassword(user, dto);
    }
}
