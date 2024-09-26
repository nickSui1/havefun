package priv.nick.cbs.topgun.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import priv.nick.cbs.topgun.dao.RoleMapper;
import priv.nick.cbs.topgun.dao.RolePermissionMapper;
import priv.nick.cbs.topgun.dao.UserMapper;
import priv.nick.cbs.topgun.dao.UserRoleMapper;
import priv.nick.cbs.topgun.model.User;

/**
 * @author nick.sui
 * @since 2024-09-22
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {
    private UserMapper userMapper;
    private RoleMapper roleMapper;
    private UserRoleMapper userRoleMapper;
    private RolePermissionMapper rolePermissionMapper;

    @Autowired
    public CustomUserDetailsService(UserMapper userMapper,
                                    RoleMapper roleMapper,
                                    UserRoleMapper userRoleMapper,
                                    RolePermissionMapper rolePermissionMapper) {
        this.userMapper = userMapper;
        this.roleMapper = roleMapper;
        this.userRoleMapper = userRoleMapper;
        this.rolePermissionMapper = rolePermissionMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.findByUsername(username);
        return new CustomUserDetails(user,roleMapper,userRoleMapper,rolePermissionMapper);
    }
}
