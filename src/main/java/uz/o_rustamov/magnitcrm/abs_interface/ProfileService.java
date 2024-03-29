package uz.o_rustamov.magnitcrm.abs_interface;

import org.springframework.http.HttpEntity;
import uz.o_rustamov.magnitcrm.ApiResponse;
import uz.o_rustamov.magnitcrm.entity.User;
import uz.o_rustamov.magnitcrm.payload.ChangePasswordDto;

public interface ProfileService {

    HttpEntity<ApiResponse> getMe(User user);

    HttpEntity<ApiResponse> changePassword(User user, ChangePasswordDto dto);

    HttpEntity<ApiResponse> setFirebaseDeviceToken(User user, String deviceToken);

}
