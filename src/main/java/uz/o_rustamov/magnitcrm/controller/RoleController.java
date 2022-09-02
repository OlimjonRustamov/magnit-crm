package uz.o_rustamov.magnitcrm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.o_rustamov.magnitcrm.payload.RoleDto;
import uz.o_rustamov.magnitcrm.service.RoleServiceImpl;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/role")
public class RoleController {

    @Autowired
    RoleServiceImpl roleService;

    @PreAuthorize(value = "hasAuthority('ADD_ROLE')")
    @PostMapping
    public HttpEntity<?> addRole(@Valid @RequestBody RoleDto dto) {
        return roleService.addRole(dto);
    }

    @PreAuthorize(value = "hasAuthority('VIEW_ROLES')")
    @GetMapping
    public HttpEntity<?> viewRoles() {
        return roleService.getRoles();
    }

    @PreAuthorize(value = "hasAuthority('DELETE_ROLE')")
    @DeleteMapping(value = "{id}")
    public HttpEntity<?> deleteRole(@PathVariable long id) {
        return roleService.deleteRole(id);
    }
}
