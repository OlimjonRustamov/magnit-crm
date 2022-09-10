package uz.o_rustamov.magnitcrm.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;


@Data
public class RecipientDto {

    @NotNull(message = "name bo'sh bo'lishi mumkin emas")
    String name;

    @JsonProperty("user_id")
    Long userId;

}
