package priv.nick.cbs.topgun.model;

import lombok.Data;
import priv.nick.cbs.topgun.model.base.BaseEntity;

/**
 * @author nick.sui
 * @since 2024-08-14
 * 用户表
 */
@Data
public class User extends BaseEntity {
    private String username;
    private String password;
    private String mobilePhone;
    private String email;
}
