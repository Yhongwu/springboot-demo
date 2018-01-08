package com.howard.springboot_shiro.service;

import com.howard.springboot_shiro.domain.User;

public interface UserService {
    User findByUserName(String username);
}
