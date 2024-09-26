package priv.nick.cbs.topgun.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import priv.nick.cbs.topgun.model.UserRole;

@Repository
@Mapper
public interface UserRoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserRole row);

    UserRole selectByPrimaryKey(Long id);

    List<UserRole> selectAll();

    int updateByPrimaryKey(UserRole row);

    List<UserRole> selectByUserId(Long userId);
}