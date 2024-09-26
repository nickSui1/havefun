package priv.nick.cbs.topgun.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import priv.nick.cbs.topgun.model.RolePermission;
import priv.nick.cbs.topgun.service.bo.RolePermissionBo;

@Repository
@Mapper
public interface RolePermissionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RolePermission row);

    RolePermission selectByPrimaryKey(Long id);

    List<RolePermission> selectAll();

    int updateByPrimaryKey(RolePermission row);

    List<RolePermission> selectByRoleId(@Param("roleId") Long roleId);

    List<RolePermission> selectByRoleIds(@Param("roleIds") List<Long> roleIds);

    List<RolePermissionBo> selectRolePermissionByRoleIds(@Param("roleIds") List<Long> roleIds);
}