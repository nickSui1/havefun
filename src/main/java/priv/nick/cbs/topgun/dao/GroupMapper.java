package priv.nick.cbs.topgun.dao;

import java.util.List;
import priv.nick.cbs.topgun.model.Group;

public interface GroupMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Group row);

    Group selectByPrimaryKey(Long id);

    List<Group> selectAll();

    int updateByPrimaryKey(Group row);
}