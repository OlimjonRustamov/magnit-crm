package uz.o_rustamov.magnitcrm.error_handling;

import lombok.Data;

public class ReverseException extends RuntimeException {

    String errorMessage;

    public ReverseException(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
