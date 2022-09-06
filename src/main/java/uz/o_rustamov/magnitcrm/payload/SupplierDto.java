package uz.o_rustamov.magnitcrm.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class SupplierDto {

    @NotNull(message = "Name bo'sh bo'lishi mumkin emas")
    private String name;
}
