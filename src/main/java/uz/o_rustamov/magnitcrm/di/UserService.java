package uz.o_rustamov.magnitcrm.di;

import org.springframework.http.HttpEntity;
import uz.o_rustamov.magnitcrm.ApiResponse;
import uz.o_rustamov.magnitcrm.entity.User;
import uz.o_rustamov.magnitcrm.payload.UserDto;

import java.util.List;

public interface UserService {

    HttpEntity<ApiResponse> getUsers();
    HttpEntity<ApiResponse> getUser(long id);
    HttpEntity<ApiResponse> addUser(UserDto dto);
    HttpEntity<ApiResponse> editUser(long id, UserDto dto);
    HttpEntity<ApiResponse> deleteUser(long id);

    HttpEntity<ApiResponse> changePassword(long id, String newPassword);

}
