package com.howard.datasource.service;

import com.howard.datasource.entity.User;

public interface UserService {
    int insert(User user);
    User findByName(String username);
}
