package priv.nick.cbs.topgun.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import priv.nick.cbs.topgun.dao.RoleMapper;
import priv.nick.cbs.topgun.dao.RolePermissionMapper;
import priv.nick.cbs.topgun.dao.UserRoleMapper;
import priv.nick.cbs.topgun.model.Role;
import priv.nick.cbs.topgun.model.User;
import priv.nick.cbs.topgun.model.UserRole;
import priv.nick.cbs.topgun.service.bo.RolePermissionBo;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author nick.sui
 * @since 2024-09-22
 */
@Service
public class CustomUserDetails implements UserDetails {
    private final User user;
    private RoleMapper roleMapper;
    private UserRoleMapper userRoleMapper;
    private RolePermissionMapper rolePermissionMapper;

    public CustomUserDetails(User user,
                             RoleMapper roleMapper,
                             UserRoleMapper userRoleMapper,
                             RolePermissionMapper rolePermissionMapper) {
        this.user = user;
        this.roleMapper = roleMapper;
        this.userRoleMapper = userRoleMapper;
        this.rolePermissionMapper = rolePermissionMapper;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //Get Roles and permissions from database
        Map<String,List<String>> rolePermissions = this.getRoleAndPermissions();

        List<GrantedAuthority> authorities = new ArrayList<>();
        //Add all roles and permissions into authorities list for Spring Security authorization required.
        rolePermissions.forEach((role,permissions)->{
            authorities.add(new SimpleGrantedAuthority(role));
            if(permissions.size()>0){
                permissions.forEach(permission->
                                            authorities.add(new SimpleGrantedAuthority(permission)));
            }
        });

        return authorities;
    }

    public Map<String,List<String>> getRoleAndPermissions() {
        List<UserRole> userRoles = userRoleMapper.selectByUserId(user.getId());
        List<Long> roleIds = userRoles.stream()
                                      .map(UserRole::getRoleId)
                                      .toList();

        //Gets all permissions associated with the role id from the dataBase
        Map<String,List<String>> rolePermissions = rolePermissionMapper.selectRolePermissionByRoleIds(roleIds)
               .stream()
               .collect(Collectors.groupingBy(
                       RolePermissionBo::getRoleName,
                       Collectors.mapping(RolePermissionBo::getPermissionName,
                                          Collectors.filtering(Objects::nonNull,Collectors.toList()))
               ));

        return rolePermissions;
    }

    private List<String> addPrefix(List<String> list, String prefix) {
        List<String> prefixList = new ArrayList<>();
        for(String s : list) {
            if(!s.startsWith(prefix)) {
                prefixList.add(prefix+s);
            }else{
                prefixList.add(s);
            }
        }

        return prefixList;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
