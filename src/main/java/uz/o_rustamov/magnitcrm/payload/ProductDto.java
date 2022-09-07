package uz.o_rustamov.magnitcrm.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ProductDto {

    @NotNull(message = "name bo'sh bo'lishi mumkin emas")
    String name;

    @NotNull(message = "balance bo'sh bo'lishi mumkin emas")
    int balance;

    @NotNull(message = "supplier_id bo'sh bo'lishi mumkin emas")
    @JsonProperty("supplier_id")
    Long supplierId;

    @NotNull(message = "count_in_box bo'sh bo'lishi mumkin emas")
    @JsonProperty("count_in_box")
    int countInBox;

    @NotNull(message = "cost_for_driver bo'sh bo'lishi mumkin emas")
    @JsonProperty("cost_for_driver")
    int costForDriver;

    @NotNull(message = "cost_for_client bo'sh bo'lishi mumkin emas")
    @JsonProperty("cost_for_client")
    int costForClient;

    @NotNull(message = "received_cost bo'sh bo'lishi mumkin emas")
    @JsonProperty("received_cost")
    int receivedCost;
}
