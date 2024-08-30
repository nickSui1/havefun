package priv.nick.cbs.topgun.model;

import lombok.Data;
import priv.nick.cbs.topgun.model.base.BaseEntity;

/**
 * 集团下属商户
 */
@Data
public class Property extends BaseEntity {
    private String name;

    private String code;

    private String contactName;

    private String contactPhone;

    private String contactEmail;

    private String city;

    private String province;

    private String country;

    private String groupFlag;

    private String description;
}
