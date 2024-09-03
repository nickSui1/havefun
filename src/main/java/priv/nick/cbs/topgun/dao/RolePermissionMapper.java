package priv.nick.cbs.topgun.dao;

import java.util.List;
import priv.nick.cbs.topgun.model.RolePermission;

public interface RolePermissionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RolePermission row);

    RolePermission selectByPrimaryKey(Long id);

    List<RolePermission> selectAll();

    int updateByPrimaryKey(RolePermission row);
}