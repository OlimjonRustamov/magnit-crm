package uz.o_rustamov.magnitcrm.di;

import org.springframework.http.HttpEntity;
import uz.o_rustamov.magnitcrm.ApiResponse;
import uz.o_rustamov.magnitcrm.payload.LoginDto;

public interface AuthService {

    public HttpEntity<ApiResponse> login(LoginDto dto);

}
