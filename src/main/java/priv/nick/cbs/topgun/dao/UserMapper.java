package priv.nick.cbs.topgun.dao;

import priv.nick.cbs.topgun.model.User;

public interface UserMapper {
    Long createUser(User user);
    User findByUsername(String username,Long groupId);
}
