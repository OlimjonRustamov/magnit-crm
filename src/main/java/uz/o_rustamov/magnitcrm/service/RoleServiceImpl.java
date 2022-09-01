package uz.o_rustamov.magnitcrm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.o_rustamov.magnitcrm.ApiResponse;
import uz.o_rustamov.magnitcrm.di.RoleService;
import uz.o_rustamov.magnitcrm.entity.Role;
import uz.o_rustamov.magnitcrm.payload.RoleDto;
import uz.o_rustamov.magnitcrm.repository.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;

    @Override
    public HttpEntity<ApiResponse> getRoles() {
        return ResponseEntity.ok(new ApiResponse(
                null, 200, true, roleRepository.findAll()));
    }

    @Override
    public HttpEntity<ApiResponse> addRole(RoleDto dto) {
        Role role = new Role();
        role.setName(dto.getName());
        role.setPermissionList(dto.getPermissionList());
        role = roleRepository.save(role);
        return ResponseEntity.ok(new ApiResponse(null, 200, true, "Role added"));
    }
}
