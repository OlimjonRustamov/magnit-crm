package uz.o_rustamov.magnitcrm.service;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.o_rustamov.magnitcrm.ApiResponse;
import uz.o_rustamov.magnitcrm.abs_interface.UserService;
import uz.o_rustamov.magnitcrm.entity.Role;
import uz.o_rustamov.magnitcrm.entity.User;
import uz.o_rustamov.magnitcrm.payload.UserDto;
import uz.o_rustamov.magnitcrm.repository.RoleRepository;
import uz.o_rustamov.magnitcrm.repository.UserRepository;

import java.util.Optional;

import static uz.o_rustamov.magnitcrm.Constants.*;

@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository;
    RoleRepository roleRepository;
    PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public HttpEntity<ApiResponse> getUsers() {
        return ResponseEntity.ok(new ApiResponse(null, 200, true, userRepository.findAll()));
    }

    @Override
    public HttpEntity<ApiResponse> getUser(long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent()) return NOT_FOUND;
        User user = optionalUser.get();
        return ResponseEntity.ok(new ApiResponse(null, 200, true, user));
    }

    @Override
    public HttpEntity<ApiResponse> addUser(UserDto dto) {
        Optional<Role> optionalRole = roleRepository.findById(dto.getRoleId());
        if (!optionalRole.isPresent()) return NOT_FOUND;
        if (userRepository.existsByUsername(dto.getUsername())) return ALREADY_EXIST;
        Role role = optionalRole.get();
        User user = new User();
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setEnabled(true);
        user.setFullName(dto.getFullName());
        user.setRole(role);
        user.setUsername(dto.getUsername());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user = userRepository.save(user);
        return SUCCESS;
    }

    @Override
    public HttpEntity<ApiResponse> editUser(long id, UserDto dto) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent()) return NOT_FOUND;

        if (userRepository.existsByUsername(dto.getUsername())) return ALREADY_EXIST;
        User user = optionalUser.get();
        user.setUsername(dto.getUsername());
        user.setFullName(dto.getFullName());
        user.setPhoneNumber(dto.getPhoneNumber());
        user = userRepository.save(user);
        return SUCCESS;
    }

    @Override
    public HttpEntity<ApiResponse> deleteUser(long id) {
        try {
            userRepository.deleteById(id);
            return SUCCESS;
        } catch (DataIntegrityViolationException ex) {
            return CONNECTED_WITH_OTHERS_EXCEPTION;
        } catch (Exception e) {
            return NOT_FOUND;
        }
    }

    @Override
    public HttpEntity<ApiResponse> changePassword(long id, String newPassword) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent()) return NOT_FOUND;
        User user = optionalUser.get();
        user.setPassword(passwordEncoder.encode(newPassword));
        user = userRepository.save(user);
        return SUCCESS;
    }
}
