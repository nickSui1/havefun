package priv.nick.cbs.topgun.model;

import lombok.Data;
import priv.nick.cbs.topgun.model.base.BaseEntity;


@Data
public class Client extends BaseEntity {
    private Long id;

    private Long tenantId;

    private String clientSecret;

    private Integer accessTokenExpires;

    private Integer refreshTokenLifespan;

    private String origins;

    private String description;
}