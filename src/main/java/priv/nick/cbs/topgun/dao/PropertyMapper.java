package priv.nick.cbs.topgun.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import priv.nick.cbs.topgun.model.Property;

import java.util.List;

@Repository
@Mapper
public interface PropertyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Property row);

    Property selectByPrimaryKey(Long id);

    List<Property> selectAll();

    int updateByPrimaryKey(Property row);
}
