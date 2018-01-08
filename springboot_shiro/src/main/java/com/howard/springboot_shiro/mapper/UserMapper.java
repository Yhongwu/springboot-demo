package com.howard.springboot_shiro.mapper;

import com.howard.springboot_shiro.domain.User;

public interface UserMapper {
    User findByUserName(String username);
}
