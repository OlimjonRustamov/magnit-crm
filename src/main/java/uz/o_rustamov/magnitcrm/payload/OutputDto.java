package uz.o_rustamov.magnitcrm.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class OutputDto {

    @NotNull(message = "date bo'sh bo'lishi mumkin emas")
    private String date;

    @JsonProperty("recipient_id")
    @NotNull(message = "recipient_id bo'sh bo'lishi mumkin emas")
    private Long recipientId;

    @JsonProperty("taken_money")
    @NotNull(message = "taken_money bo'sh bo'lishi mumkin emas")
    private Long takenMoney;

    private String note;

    @JsonProperty("product_list")
    @NotNull(message = "product_list bo'sh bo'lishi mumkin emas")
    private List<InputOutputProductDto> productList;

}
