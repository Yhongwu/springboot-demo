package com.howard.springboot_freemarker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class IndexController {
    @RequestMapping("index")
    public  String index(Map<String,Object> map) {
        map.put("hello","Hello FreeMarker");
        return "index";
    }
}
