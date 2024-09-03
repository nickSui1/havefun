package priv.nick.cbs.topgun.dao;

import java.util.List;
import priv.nick.cbs.topgun.model.Role;

public interface RoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Role row);

    Role selectByPrimaryKey(Long id);

    List<Role> selectAll();

    int updateByPrimaryKey(Role row);
}