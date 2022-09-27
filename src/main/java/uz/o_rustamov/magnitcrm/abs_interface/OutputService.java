package uz.o_rustamov.magnitcrm.abs_interface;

import org.springframework.http.HttpEntity;
import uz.o_rustamov.magnitcrm.ApiResponse;
import uz.o_rustamov.magnitcrm.entity.User;
import uz.o_rustamov.magnitcrm.payload.OutputDto;

public interface OutputService {

    HttpEntity<ApiResponse> getAllOutputs();

    HttpEntity<ApiResponse> getOutputById(Long id);

    HttpEntity<ApiResponse> getOutputByDate(String fromDate, String toDate);

    HttpEntity<ApiResponse> getMyOutputs(User user, int page, int size);
    HttpEntity<ApiResponse> getMyOutputsAndDate(User user, String fromDate, String toDate, int page, int size);

    HttpEntity<ApiResponse> getOutputByDateAndRecipientId(String fromDate, String toDate, Long recipientId);

    HttpEntity<ApiResponse> getAllByRecipientId(Long recipientId);

    HttpEntity<ApiResponse> addOutput(User user, OutputDto dto);

    HttpEntity<ApiResponse> deleteOutput(Long id);

    HttpEntity<ApiResponse> confirmOutput(User user, Long outputId);

}
