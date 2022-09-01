package uz.o_rustamov.magnitcrm.error_handling;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import uz.o_rustamov.magnitcrm.ApiResponse;

import javax.servlet.ServletException;

@ControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public HttpEntity<ApiResponse> handleNotValidatedException(MethodArgumentNotValidException ex) {
        FieldError error = ex.getBindingResult().getFieldError();
        String fieldName = "";
        String defaultMessage = "";
        try {
            fieldName = error.getField();
            defaultMessage = error.getDefaultMessage();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(400)
                .body(new ApiResponse(defaultMessage, 400, false, null));

    }

    @ExceptionHandler(AccessDeniedException.class)
    public HttpEntity<ApiResponse> handleForbiddenException(AccessDeniedException ex) {
        return ResponseEntity.status(403).body(new ApiResponse(
                "Sizda ushbu ma'lumotlarni ko'rish uchun ruxsat mavjud emas",
                403, false, null));

    }

    @ExceptionHandler(ServletException.class)
    public HttpEntity<ApiResponse> handleJwtExpiredException(ServletException ex) {
        return ResponseEntity.status(401).body(new ApiResponse(
                "Identifikatsiya ma'lumotlari eskirgan",
                401, false, null));

    }
}
