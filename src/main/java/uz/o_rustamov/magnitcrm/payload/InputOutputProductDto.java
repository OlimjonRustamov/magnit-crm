package uz.o_rustamov.magnitcrm.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class InputOutputProductDto {

    @JsonProperty("product_id")
    private Long productId;

    @JsonProperty("box_count")
    private int boxCount;

}

