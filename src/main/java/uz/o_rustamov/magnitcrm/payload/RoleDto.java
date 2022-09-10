package uz.o_rustamov.magnitcrm.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import uz.o_rustamov.magnitcrm.enums.Permission;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class RoleDto {

    @NotNull(message = "RoleName bo'sh bo'lishi mumkin emas")
    String name;

    @NotNull(message = "Permissions bo'sh bo'lishi mumkin emas")
    @JsonProperty("permission_list")
    List<Permission> permissionList;

}
