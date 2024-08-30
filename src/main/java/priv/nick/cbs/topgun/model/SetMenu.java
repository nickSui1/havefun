package priv.nick.cbs.topgun.model;

import lombok.Data;
import priv.nick.cbs.topgun.model.base.BaseEntity;


/**
 * @since 2024-4-18 16:52
 * @author nicksy
 */
@Data
public class SetMenu extends BaseEntity {
    /** Merchant id */
    private Long propertyId;

    /** Set Menu code */
    private String code;

    /** Name */
    private String name;

    /** Description */
    private String description;

    /** Type of service(charge per table/charge per person) */
    private String serviceType;

    /** Sort No */
    private Integer sortNo;

    @Override
    public String toString(){
        return "SetMenu [propertyId=" + propertyId + ", code=" + code + ", name=" + name + ", description="
                + description + ", serviceType=" + serviceType + ", sortNo=" + sortNo + "]";
    }
}
