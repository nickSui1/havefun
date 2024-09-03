package priv.nick.cbs.topgun.dao;

import priv.nick.cbs.topgun.model.Property;

import java.util.List;

public interface PropertyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Property row);

    Property selectByPrimaryKey(Long id);

    List<Property> selectAll();

    int updateByPrimaryKey(Property row);
}
