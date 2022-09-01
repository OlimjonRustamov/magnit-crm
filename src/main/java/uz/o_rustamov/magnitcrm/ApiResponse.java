package uz.o_rustamov.magnitcrm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {
    String error;
    int status;
    boolean success;
    Object data;
}
