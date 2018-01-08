package com.howard.springboot_shiro.shiro;

import com.howard.springboot_shiro.service.UserService;
import com.howard.springboot_shiro.domain.Permission;
import com.howard.springboot_shiro.domain.Role;
import com.howard.springboot_shiro.domain.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

public class AuthRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    /**
     * 登录认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("doGetAuthenticationInfo 登录认证");
        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;
        String username = token.getUsername();
        User user = userService.findByUserName(username);
        if (user != null) {
            //放入shiro.调用CredentialsMatcher检验密码
            //建议使用user.getUserName()
            //明文
           // return new SimpleAuthenticationInfo(user,user.getPassword(),this.getClass().getName());
            //密文
            return new SimpleAuthenticationInfo(user,user.getPassword(),ByteSource.Util.bytes(user.getSalt()),this.getClass().getName());
        }
        return null;
    }

    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("doGetAuthorizationInfo 授权");
        //获取session中的用户
        User user = (User)principalCollection.fromRealm(this.getClass().getName()).iterator().next();
        Set<String> permissions = new HashSet<>();
        Set<String> roles = new HashSet<>();
        Set<Role> rolesSet = user.getRoles();
        if (rolesSet.size() > 0) {
            for (Role role : rolesSet) {
                Set<Permission> permissionSet = role.getPermissions();
                roles.add(role.getRname());
                if (permissionSet.size() > 0) {
                    for(Permission P:permissionSet) {
                        permissions.add(P.getPname());
                    }
                }
            }
        }
        //将权限信息和角色放入shiro中
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addRoles(roles);
        info.addStringPermissions(permissions);
        return info;
    }
}
