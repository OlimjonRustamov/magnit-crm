package uz.o_rustamov.magnitcrm.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.o_rustamov.magnitcrm.ApiResponse;
import uz.o_rustamov.magnitcrm.abs_interface.ProfileService;
import uz.o_rustamov.magnitcrm.entity.User;
import uz.o_rustamov.magnitcrm.payload.ChangePasswordDto;
import uz.o_rustamov.magnitcrm.repository.UserRepository;

import static uz.o_rustamov.magnitcrm.Constants.SUCCESS;

@Service
public class ProfileServiceImpl implements ProfileService {
    UserRepository userRepository;
    PasswordEncoder passwordEncoder;

    public ProfileServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public HttpEntity<ApiResponse> getMe(User user) {
        return ResponseEntity.ok(new ApiResponse(null, 200, true, user));
    }

    @Override
    public HttpEntity<ApiResponse> changePassword(User user, ChangePasswordDto dto) {
        if (dto.getNewPassword().equals(dto.getPrePassword())) {
            user.setPassword(passwordEncoder.encode(dto.getNewPassword()));
            userRepository.save(user);
            return SUCCESS;
        } else {
            return ResponseEntity.status(400).body(new ApiResponse(
                    "Parollar mos emas", 400, false, null));
        }
    }

    @Override
    public HttpEntity<ApiResponse> setFirebaseDeviceToken(User user, String deviceToken) {
        user.setFcmToken(deviceToken);
        userRepository.save(user);
        return SUCCESS;
    }
}
