package priv.nick.cbs.topgun.service;

import priv.nick.cbs.topgun.dto.user.UserAddDTO;
import priv.nick.cbs.topgun.dto.user.UserInfoDTO;
import priv.nick.cbs.topgun.dto.user.UserUpdateDTO;

import java.util.List;

/**
 * @author nick.sui
 * @since 2024-08-28
 * 用户接口
 */
public interface UserService {
    Long createUser(UserAddDTO user);
    UserInfoDTO findByUsername(String username);
    List<UserInfoDTO> findByRole(Long roleId);
    void updateUser(UserUpdateDTO user, Long id);
    void deleteUser(Long id);
}
