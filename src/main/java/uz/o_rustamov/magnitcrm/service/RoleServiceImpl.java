package uz.o_rustamov.magnitcrm.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.o_rustamov.magnitcrm.ApiResponse;
import uz.o_rustamov.magnitcrm.abs_interface.RoleService;
import uz.o_rustamov.magnitcrm.entity.Role;
import uz.o_rustamov.magnitcrm.payload.RoleDto;
import uz.o_rustamov.magnitcrm.repository.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService {

    RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

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
        try {
            role = roleRepository.save(role);
            return ResponseEntity.ok(new ApiResponse(null, 200, true, "Role added"));
        } catch (Exception e) {
            return ResponseEntity.status(409).body(new ApiResponse("Ushbu role mavjud", 409, false, null));
        }
    }

    @Override
    public HttpEntity<ApiResponse> deleteRole(long id) {
        try {
            roleRepository.deleteById(id);
            return ResponseEntity.ok(new ApiResponse(null, 200, true, "Role deleted"));
        } catch (Exception e) {
            return ResponseEntity.status(404).body(new ApiResponse("Role not found", 404, false, null));
        }
    }
}
