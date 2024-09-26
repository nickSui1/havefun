package priv.nick.cbs.topgun.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import priv.nick.cbs.topgun.model.Group;

@Repository
@Mapper
public interface GroupMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Group row);

    Group selectByPrimaryKey(Long id);

    List<Group> selectAll();

    int updateByPrimaryKey(Group row);
}