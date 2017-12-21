package com.howard.springmvc4.web.converter;

import com.howard.springmvc4.domain.DemoObj;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ConverterController {

    /**
     * 返回指定类型为自定义的媒体类型
     * @param demoObj
     * @return
     */
    @RequestMapping(value = "/convert", produces = { "application/x-wisely" }) //1
    public @ResponseBody DemoObj convert(@RequestBody DemoObj demoObj) {

        return demoObj;

    }
}
