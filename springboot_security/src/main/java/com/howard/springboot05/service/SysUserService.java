package com.howard.springboot05.service;

import com.howard.springboot05.dao.SysUserRepository;
import com.howard.springboot05.domain.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * 自定义的userService需要实现UserDetailsService接口并重写loadUserByUsername获取用户
 */
public class SysUserService implements UserDetailsService{

    @Autowired
    private SysUserRepository sysUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser user = sysUserRepository.findByUsername(username);
        System.out.println(user.getRoles());
        if (user == null) {
            throw  new UsernameNotFoundException("用户名不存在");
        }
        //由于SysUser实现了UserDetails，所以可以直接返回
        return user;
    }

}
