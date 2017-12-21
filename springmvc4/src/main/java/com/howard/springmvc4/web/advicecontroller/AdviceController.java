package com.howard.springmvc4.web.advicecontroller;

import com.howard.springmvc4.domain.DemoObj;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdviceController {

    @RequestMapping("/advice")
    public String getSomething(@ModelAttribute("msg") String msg, DemoObj obj) {
        //调试查看demoobj中的id 可以看到id被忽略了 值为null
        throw  new IllegalArgumentException("非常抱歉，参数有误,来自@ModelAttribute:"+msg);
    }
}
