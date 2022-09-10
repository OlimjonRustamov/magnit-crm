package uz.o_rustamov.magnitcrm.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserDto {

    @NotNull(message = "Username bo'sh bo'lishi mumkin emas")
    String username;

    @NotNull(message = "Parol bo'sh bo'lishi mumkin emas")
    String password;

    @NotNull(message = "Telefon raqam bo'sh bo'lishi mumkin emas")
    @JsonProperty("phone_number")
    String phoneNumber;

    @NotNull(message = "FULL NAME bo'sh bo'lishi mumkin emas")
    @JsonProperty("full_name")
    String fullName;

    @NotNull(message = "RoleId bo'sh bo'lishi mumkin emas")
    @JsonProperty("role_id")
    Long roleId;

}
