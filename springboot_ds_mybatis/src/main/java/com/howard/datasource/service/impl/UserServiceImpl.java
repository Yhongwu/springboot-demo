package com.howard.datasource.service.impl;


import com.howard.datasource.common.annotation.DataSource;
import com.howard.datasource.entity.User;
import com.howard.datasource.mapper.UserMapper;
import com.howard.datasource.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @DataSource("datasource2")
    @Override
    public int insert(User user) {
        //指定使用数据源
        //DatabaseContextHolder.setDatabaseType(DatabaseType.datasource1);
        return userMapper.insert(user);
    }



    @Override
    @DataSource("datasource1")
    public User findByName(String username) {
        return userMapper.findByName(username);
    }

}
