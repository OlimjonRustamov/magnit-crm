package uz.o_rustamov.magnitcrm.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class InputDto {

    @NotNull(message = "date bo'sh bo'lishi mumkin emas")
    private String date;

    @JsonProperty("supplier_id")
    @NotNull(message = "supplier_id bo'sh bo'lishi mumkin emas")
    private Long supplierId;

    @JsonProperty("given_money")
    @NotNull(message = "given_money bo'sh bo'lishi mumkin emas")
    private Long givenMoney;

    private String note;

    @JsonProperty("product_list")
    @NotNull(message = "product_list bo'sh bo'lishi mumkin emas")
    private List<InputProductDto> productList;

}
