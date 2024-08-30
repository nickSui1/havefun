package priv.nick.cbs.topgun.model;

import lombok.Data;
import priv.nick.cbs.topgun.model.base.BaseEntity;

/**
 * @author nick.sui
 * @since 2024-08-19
 * 客户表
 */
@Data
public class Customer extends BaseEntity {
    private String fullName;
    private String firstname;
    private String lastname;
    private String phone;
    private String email;
    private String address;
    private String city;
    private String province;
    private String country;
    private String zip;
    private String IDType;
    private String IDNumber;
}
