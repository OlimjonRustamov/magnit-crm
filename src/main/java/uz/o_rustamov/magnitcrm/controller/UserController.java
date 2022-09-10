package uz.o_rustamov.magnitcrm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import uz.o_rustamov.magnitcrm.ApiResponse;
import uz.o_rustamov.magnitcrm.payload.UserDto;
import uz.o_rustamov.magnitcrm.service.UserServiceImpl;

import javax.validation.Valid;

@Transactional
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @PreAuthorize(value = "hasAuthority('VIEW_USERS')")
    @GetMapping
    public HttpEntity<ApiResponse> getUsers() {
        return userService.getUsers();
    }

    @PreAuthorize(value = "hasAuthority('VIEW_USERS')")
    @GetMapping("/{id}")
    public HttpEntity<ApiResponse> getUserById(@PathVariable long id) {
        return userService.getUser(id);
    }

    @PreAuthorize(value = "hasAuthority('ADD_USER')")
    @PostMapping
    public HttpEntity<ApiResponse> addUser(@Valid @RequestBody UserDto dto) {
        return userService.addUser(dto);
    }


    @PreAuthorize(value = "hasAuthority('EDIT_USER')")
    @PutMapping("/{id}")
    public HttpEntity<ApiResponse> editUser(@Valid @RequestBody UserDto dto, @PathVariable long id) {
        return userService.editUser(id, dto);
    }

    @PreAuthorize(value = "hasAuthority('DELETE_USER')")
    @DeleteMapping("/{id}")
    public HttpEntity<ApiResponse> deleteUser(@PathVariable long id) {
        return userService.deleteUser(id);
    }
}
