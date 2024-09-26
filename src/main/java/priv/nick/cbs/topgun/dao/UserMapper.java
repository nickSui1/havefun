package priv.nick.cbs.topgun.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import priv.nick.cbs.topgun.model.User;

@Repository
@Mapper
public interface UserMapper {
    Long createUser(User user);
    User findByUsername(String username);
}
