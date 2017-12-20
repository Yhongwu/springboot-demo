package com.howard.springmvc4.web.restcontroller;

import com.howard.springmvc4.domain.DemoObj;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController //组合注解 包括@Controller、@ResponseBody
@RequestMapping("/rest")
public class DemoRestController {

    //返回对象自动转换为json

    @RequestMapping(value = "/getjson", produces = "application/json;charset=utf-8")
    public DemoObj getJson(DemoObj obj) {
        return new DemoObj(12L, "hh");
    }
    @RequestMapping(value = "/getxml", produces = "application/xml;charset=utf-8")
    public DemoObj getxml(DemoObj obj) {
        return new DemoObj(2L, "jj");
    }
}
