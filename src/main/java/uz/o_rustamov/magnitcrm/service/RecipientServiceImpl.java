package uz.o_rustamov.magnitcrm.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.o_rustamov.magnitcrm.ApiResponse;
import uz.o_rustamov.magnitcrm.abs_interface.RecipientService;
import uz.o_rustamov.magnitcrm.entity.Recipient;
import uz.o_rustamov.magnitcrm.entity.User;
import uz.o_rustamov.magnitcrm.payload.RecipientDto;
import uz.o_rustamov.magnitcrm.repository.RecipientRepository;
import uz.o_rustamov.magnitcrm.repository.UserRepository;

import java.util.Optional;

import static uz.o_rustamov.magnitcrm.Constants.*;

@Service
public class RecipientServiceImpl implements RecipientService {

    RecipientRepository recipientRepository;
    UserRepository userRepository;

    public RecipientServiceImpl(RecipientRepository recipientRepository, UserRepository userRepository) {
        this.recipientRepository = recipientRepository;
        this.userRepository = userRepository;
    }

    @Override
    public HttpEntity<ApiResponse> getAllRecipients() {
        return ResponseEntity.ok(new ApiResponse(
                null, 200, true, recipientRepository.findAll()));
    }

    @Override
    public HttpEntity<ApiResponse> getRecipientById(Long id) {
        if (recipientRepository.existsById(id))
            return ResponseEntity.ok(new ApiResponse(
                    null, 200, true, recipientRepository.findById(id)));
        return NOT_FOUND;
    }

    @Override
    public HttpEntity<ApiResponse> editRecipient(Long id, RecipientDto dto) {
        if (recipientRepository.existsByName(dto.getName())) {
            return ALREADY_EXIST;
        }
        Optional<Recipient> optionalRecipient = recipientRepository.findById(id);
        if (optionalRecipient.isPresent()) {
            Recipient recipient = optionalRecipient.get();
            recipient.setName(dto.getName());
            recipient = recipientRepository.save(recipient);
            return SUCCESS;
        } else return NOT_FOUND;
    }

    @Override
    public HttpEntity<ApiResponse> addRecipient(RecipientDto dto) {
        if (recipientRepository.existsByName(dto.getName())) {
            return ALREADY_EXIST;
        }
        Recipient recipient = new Recipient();
        if (dto.getUserId() != null) {
            Optional<User> optionalUser = userRepository.findById(dto.getUserId());
            optionalUser.ifPresent(recipient::setUser);
        }
        recipient.setName(dto.getName());
        recipient = recipientRepository.save(recipient);
        return SUCCESS;
    }

    @Override
    public HttpEntity<ApiResponse> deleteRecipient(Long id) {
        try {
            recipientRepository.deleteById(id);
            return SUCCESS;
        } catch (Exception e) {
            return ResponseEntity.status(409).body(new ApiResponse(
                    e.getMessage(), 409, false, null));
        }
    }
}
