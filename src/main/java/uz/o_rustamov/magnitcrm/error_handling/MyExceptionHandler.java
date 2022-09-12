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

import static uz.o_rustamov.magnitcrm.Constants.TOKEN_EXPIRED;
import static uz.o_rustamov.magnitcrm.Constants.YOU_DONT_HAVE_ACCESS;

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
        return YOU_DONT_HAVE_ACCESS;

    }

    @ExceptionHandler(ServletException.class)
    public HttpEntity<ApiResponse> handleJwtExpiredException(ServletException ex) {
        return TOKEN_EXPIRED;

    }

    @ExceptionHandler(ReverseException.class)
    public HttpEntity<ApiResponse> handleExToReverse(ReverseException ex) {
        return ResponseEntity.status(406).body(new ApiResponse(
                ex.errorMessage,
                406, false, null));

    }
}
