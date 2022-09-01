package uz.o_rustamov.magnitcrm.payload;

import lombok.Data;
import uz.o_rustamov.magnitcrm.enums.Permission;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class RoleDto {

    @NotNull(message = "RoleName bo'sh bo'lishi mumkin emas")
    String name;
    @NotNull(message = "Permissions bo'sh bo'lishi mumkin emas")
    List<Permission> permissionList;

}
