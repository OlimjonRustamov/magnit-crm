package uz.o_rustamov.magnitcrm.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ChangePasswordDto {

    @NotNull(message = "newPassword bo'sh bo'lishi mumkin emas")
    String newPassword;
    @NotNull(message = "prePassword bo'sh bo'lishi mumkin emas")
    String prePassword;


}
