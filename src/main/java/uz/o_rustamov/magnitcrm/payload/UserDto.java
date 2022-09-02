package uz.o_rustamov.magnitcrm.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserDto {

    @NotNull(message = "Username bo'sh bo'lishi mumkin emas")
    String username;
    @NotNull(message = "Parol bo'sh bo'lishi mumkin emas")
    String password;
    @NotNull(message = "Telefon raqam bo'sh bo'lishi mumkin emas")
    String phoneNumber;
    @NotNull(message = "FULL NAME bo'sh bo'lishi mumkin emas")
    String fullName;
    @NotNull(message = "RoleId bo'sh bo'lishi mumkin emas")
    Long roleId;

}
