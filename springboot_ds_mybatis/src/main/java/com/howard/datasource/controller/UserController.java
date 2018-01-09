package com.howard.datasource.controller;

import com.howard.datasource.entity.User;
import com.howard.datasource.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("add")
    public Integer addUser(User user) {
        int result = userService.insert(user);
        return result;
    }

    @RequestMapping("find")
    public User findByName(String username) {
        User user = userService.findByName(username);
        return user;
    }
}
