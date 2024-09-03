package priv.nick.cbs.topgun.model;

import lombok.Data;
import priv.nick.cbs.topgun.model.base.BaseEntity;

@Data
public class RolePermission extends BaseEntity {
    private Long id;

    private String permission;

    private Long roleId;

    private Long propertyId;
}