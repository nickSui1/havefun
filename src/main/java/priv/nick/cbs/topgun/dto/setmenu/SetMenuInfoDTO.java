package priv.nick.cbs.topgun.dto.setmenu;

import lombok.Data;
import priv.nick.cbs.topgun.dto.base.BaseEntityDTO;

import java.io.Serial;
import java.io.Serializable;

@Data
public class SetMenuInfoDTO extends BaseEntityDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 6476095625397372226L;
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
    
    private Long propertyId;
    
    private Long tenantId;

    @Override
    public String toString(){
        return "SetMenu [propertyId=" + propertyId + ", code=" + code + ", name=" + name + ", description="
                + description + ", serviceType=" + serviceType + ", sortNo=" + sortNo + ", tenantId="+tenantId+
                ", createdBy="+getCreatedBy() +", createdAt="+ getCreatedAt() +
                ", updatedBy="+getUpdatedBy() +", updatedAt="+ getUpdatedAt() +"]";
    }
}
