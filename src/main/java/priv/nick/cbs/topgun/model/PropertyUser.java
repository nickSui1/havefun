package priv.nick.cbs.topgun.model;

import lombok.Data;
import priv.nick.cbs.topgun.model.base.BaseEntity;

@Data
public class PropertyUser extends BaseEntity {
    private Long id;

    private Long groupId;

    private Long propertyId;

    private Long userId;
}