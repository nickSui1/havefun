package priv.nick.cbs.topgun.domain;

import lombok.Data;
import priv.nick.cbs.topgun.domain.base.BaseEntity;


/**
 * 集团，组
 */
@Data
public class Group extends BaseEntity {
    private String name;

    @Override
    public String toString(){
        return "Group [name=" + name + "]";
    }
}
