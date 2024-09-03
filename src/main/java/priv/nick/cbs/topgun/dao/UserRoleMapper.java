package priv.nick.cbs.topgun.dao;

import java.util.List;
import priv.nick.cbs.topgun.model.UserRole;

public interface UserRoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserRole row);

    UserRole selectByPrimaryKey(Long id);

    List<UserRole> selectAll();

    int updateByPrimaryKey(UserRole row);
}