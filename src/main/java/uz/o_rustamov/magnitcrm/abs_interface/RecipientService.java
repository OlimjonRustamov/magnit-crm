package uz.o_rustamov.magnitcrm.abs_interface;

import org.springframework.http.HttpEntity;
import uz.o_rustamov.magnitcrm.ApiResponse;
import uz.o_rustamov.magnitcrm.payload.RecipientDto;

public interface RecipientService {

    HttpEntity<ApiResponse> getAllRecipients();

    HttpEntity<ApiResponse> getRecipientById(Long id);

    HttpEntity<ApiResponse> editRecipient(Long id, RecipientDto dto);

    HttpEntity<ApiResponse> addRecipient(RecipientDto dto);

    HttpEntity<ApiResponse> deleteRecipient(Long id);

}
