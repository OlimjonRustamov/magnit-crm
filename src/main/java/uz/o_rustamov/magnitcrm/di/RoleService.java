package uz.o_rustamov.magnitcrm.di;

import org.springframework.http.HttpEntity;
import uz.o_rustamov.magnitcrm.ApiResponse;
import uz.o_rustamov.magnitcrm.payload.RoleDto;

public interface RoleService {

    HttpEntity<ApiResponse> getRoles();

    HttpEntity<ApiResponse> addRole(RoleDto dto);

    HttpEntity<ApiResponse> deleteRole(long id);
}
