package uz.o_rustamov.magnitcrm.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;


@Data
public class RecipientDto {

    @NotNull(message = "name bo'sh bo'lishi mumkin emas")
    String name;

    Long userId;

}
