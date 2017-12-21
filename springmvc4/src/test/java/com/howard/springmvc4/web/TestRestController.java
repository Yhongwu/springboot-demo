package com.howard.springmvc4.web;

import com.howard.springmvc4.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestRestController {
    @Autowired
    private TestService service;

    @RequestMapping(value = "/testRest",produces = "text/plain;charset=UTF-8")
    public String testRest() {
        return service.saySomething();
    }
}
