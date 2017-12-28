package com.howard.springboot10.controller;

import com.howard.springboot10.dao.UserMapper;
import com.howard.springboot10.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @RequestMapping("find_by_name")
    public User findByName() {
        System.out.println("===");
        return userMapper.findByName("howard");
    }

    @RequestMapping("save")
    public int saveUser() {
        User u = new User(3,"tom2",25);
        return userMapper.insert(u);
    }
}
