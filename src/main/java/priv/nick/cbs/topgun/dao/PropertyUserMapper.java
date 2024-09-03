package priv.nick.cbs.topgun.dao;

import java.util.List;
import priv.nick.cbs.topgun.model.PropertyUser;

public interface PropertyUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PropertyUser row);

    PropertyUser selectByPrimaryKey(Long id);

    List<PropertyUser> selectAll();

    int updateByPrimaryKey(PropertyUser row);
}