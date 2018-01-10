package com.howard.springboot_exception.controller;

import com.howard.springboot_exception.exception.MyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
    @RequestMapping("/")
    public String index() {
        return "index";
    }
    @RequestMapping("/test1")
    public String test() {
        int i = 1/0;
        return  "hello";
    }

    @RequestMapping("error1")
    public String error() {
        return  "myerror";
    }


    @RequestMapping("/test2")
    public String test2() throws MyException {
        throw new MyException();
    }

}
