package uz.o_rustamov.magnitcrm.abs_interface;

import org.springframework.http.HttpEntity;
import uz.o_rustamov.magnitcrm.ApiResponse;
import uz.o_rustamov.magnitcrm.entity.User;
import uz.o_rustamov.magnitcrm.payload.InputDto;

public interface InputService {

    HttpEntity<ApiResponse> getAllInputs(int page, int size);

    HttpEntity<ApiResponse> getInputById(Long id);

    HttpEntity<ApiResponse> getInputByDate(String fromDate, String toDate, int page, int size);

    HttpEntity<ApiResponse> getAllBySupplierId(Long id, int page, int size);

    HttpEntity<ApiResponse> getAllByPeriodAndSupplierId(String fromDate, String toDate, Long id, int page, int size);

    HttpEntity<ApiResponse> addInput(User user, InputDto dto);

    HttpEntity<ApiResponse> deleteInput(Long id);

}
