package priv.nick.cbs.topgun.model;

import lombok.Data;
import priv.nick.cbs.topgun.model.base.BaseEntity;

import java.util.StringJoiner;


/**
 * 集团，组
 */
@Data
public class Group extends BaseEntity {
    private String name;

    private String code;

    private String contactName;

    private String contactPhone;

    private String contactEmail;

    private Boolean multiProperty;

    private Integer numberOfProperty;

    private String description;

    @Override
    public String toString() {
        return new StringJoiner(", ", Group.class.getSimpleName() + "[", "]").toString();
    }
}
