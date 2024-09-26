package priv.nick.cbs.topgun.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import priv.nick.cbs.topgun.model.PropertyUser;

@Repository
@Mapper
public interface PropertyUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PropertyUser row);

    PropertyUser selectByPrimaryKey(Long id);

    List<PropertyUser> selectAll();

    int updateByPrimaryKey(PropertyUser row);
}