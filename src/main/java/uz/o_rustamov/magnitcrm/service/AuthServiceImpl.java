package uz.o_rustamov.magnitcrm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.o_rustamov.magnitcrm.ApiResponse;
import uz.o_rustamov.magnitcrm.configuration.JwtProvider;
import uz.o_rustamov.magnitcrm.di.AuthService;
import uz.o_rustamov.magnitcrm.entity.User;
import uz.o_rustamov.magnitcrm.payload.LoginDto;
import uz.o_rustamov.magnitcrm.repository.RoleRepository;
import uz.o_rustamov.magnitcrm.repository.UserRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {
    UserRepository userRepository;
    RoleRepository roleRepository;
    PasswordEncoder passwordEncoder;
    JwtProvider jwtProvider;

    public AuthServiceImpl(JwtProvider jwtProvider, PasswordEncoder passwordEncoder, RoleRepository roleRepository, UserRepository userRepository) {
        this.jwtProvider = jwtProvider;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @Override
    public HttpEntity<ApiResponse> login(LoginDto dto) {
        Optional<User> optionalUser = userRepository.findByUsername(dto.getUsername());
        if (!optionalUser.isPresent())
            return ResponseEntity.status(404).body(new ApiResponse(
                    "Username mavjud emas",
                    404, false, null));
        User user = optionalUser.get();
        boolean equal = passwordEncoder.matches(dto.getPassword(), user.getPassword());
        if (equal) {
            String token = jwtProvider.generateToken(dto.getUsername());
            Map<String, String> map = new HashMap<>();
            map.put("api_token", token);
            return ResponseEntity.ok(new ApiResponse(null, 200, true, map));
        }
        return ResponseEntity.status(409).body(
                new ApiResponse("Parol xato", 409, false, null));
    }
}
