package uz.o_rustamov.magnitcrm.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ChangePasswordDto {

    @NotNull(message = "newPassword bo'sh bo'lishi mumkin emas")
    @JsonProperty("new_password")
    String newPassword;

    @NotNull(message = "prePassword bo'sh bo'lishi mumkin emas")
    @JsonProperty("pre_password")
    String prePassword;


}
