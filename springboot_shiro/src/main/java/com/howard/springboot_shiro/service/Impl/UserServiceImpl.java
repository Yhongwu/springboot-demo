package com.howard.springboot_shiro.service.Impl;

import com.howard.springboot_shiro.service.UserService;
import com.howard.springboot_shiro.domain.User;
import com.howard.springboot_shiro.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper usermapper;

    @Override
    public User findByUserName(String username) {
        return usermapper.findByUserName(username);
    }
}
