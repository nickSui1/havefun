package priv.nick.cbs.topgun.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import priv.nick.cbs.topgun.model.Role;

@Repository
@Mapper
public interface RoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Role row);

    Role selectByPrimaryKey(Long id);

    List<Role> selectAll();

    int updateByPrimaryKey(Role row);

    List<Role> selectByIds(@Param("ids") List<Long> ids);
}