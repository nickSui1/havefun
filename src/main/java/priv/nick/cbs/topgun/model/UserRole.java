package priv.nick.cbs.topgun.model;

import lombok.Data;
import priv.nick.cbs.topgun.model.base.BaseEntity;


@Data
public class UserRole extends BaseEntity {
    private Long id;

    private Long userId;

    private Long roleId;
}