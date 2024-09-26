package priv.nick.cbs.topgun.service.bo;

import lombok.Data;

@Data
public class RolePermissionBo {
    private Long id;
    private String roleName;
    private String permissionName;
}
