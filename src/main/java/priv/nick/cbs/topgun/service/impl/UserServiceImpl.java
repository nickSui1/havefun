package priv.nick.cbs.topgun.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import priv.nick.cbs.topgun.dao.UserMapper;
import priv.nick.cbs.topgun.dto.user.UserAddDTO;
import priv.nick.cbs.topgun.dto.user.UserInfoDTO;
import priv.nick.cbs.topgun.dto.user.UserUpdateDTO;
import priv.nick.cbs.topgun.model.User;
import priv.nick.cbs.topgun.service.UserService;
import priv.nick.cbs.topgun.util.SnowflakeIdWorker;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private UserMapper userMapper;
    private SnowflakeIdWorker snowflakeIdWorker;
    @Autowired
    public UserServiceImpl(UserMapper userMapper,
                           SnowflakeIdWorker snowflakeIdWorker){
        this.userMapper=userMapper;
        this.snowflakeIdWorker=snowflakeIdWorker;
    }
    @Override
    public Long createUser(UserAddDTO user) {
        ModelMapper modelMapper = new ModelMapper();
        User userEntity = modelMapper.map(user, User.class);
        Long id = snowflakeIdWorker.nextId();
        userEntity.setId(id);
        userEntity.setCreatedBy("admin");
        userEntity.setCreateTime(LocalDateTime.now().toInstant(ZoneOffset.UTC));

        Long userId = userMapper.createUser(userEntity);

        return userId;
    }

    @Override
    public UserInfoDTO findByUsername(String username) {
        return null;
    }

    @Override
    public List<UserInfoDTO> findByRole(Long roleId) {
        return List.of();
    }

    @Override
    public void updateUser(UserUpdateDTO user, Long id) {

    }

    @Override
    public void deleteUser(Long id) {

    }
}
