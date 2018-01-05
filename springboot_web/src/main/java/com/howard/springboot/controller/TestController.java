package com.howard.springboot.controller;

import com.howard.springboot.dao.TestMapper;
import com.howard.springboot.domain.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private TestMapper testMapper;

    @RequestMapping("find_by_name")
    public Test findByName() {
        System.out.println("===");
        return testMapper.findByName("howard");
    }

    @RequestMapping("save")
    public int saveUser() {
        Test u = new Test(3,"tom2",25);
        return testMapper.insert(u);
    }
}
