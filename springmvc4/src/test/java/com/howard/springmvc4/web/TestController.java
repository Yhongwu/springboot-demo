package com.howard.springmvc4.web;

import com.howard.springmvc4.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {
    @Autowired
    private TestService service;

    @RequestMapping("/testpage")
    public String testPage(Model model) {
        model.addAttribute("msg",service.saySomething());
        return "test";
    }
}
