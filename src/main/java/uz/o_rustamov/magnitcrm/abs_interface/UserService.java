package uz.o_rustamov.magnitcrm.abs_interface;

import org.springframework.http.HttpEntity;
import uz.o_rustamov.magnitcrm.ApiResponse;
import uz.o_rustamov.magnitcrm.payload.UserDto;

public interface UserService {

    HttpEntity<ApiResponse> getUsers();
    HttpEntity<ApiResponse> getUser(long id);
    HttpEntity<ApiResponse> addUser(UserDto dto);
    HttpEntity<ApiResponse> editUser(long id, UserDto dto);
    HttpEntity<ApiResponse> deleteUser(long id);

    HttpEntity<ApiResponse> changePassword(long id, String newPassword);

}
