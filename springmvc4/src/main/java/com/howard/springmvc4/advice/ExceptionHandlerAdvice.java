package com.howard.springmvc4.advice;

import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

/**
 * 控制器建言
 * 在此处配置对所有控制器方法都生效
 */
@ControllerAdvice
public class ExceptionHandlerAdvice {

    /**
     * @ExceptionHandler ： 全局处理异常控制器
     * value = Exception.class ：拦截所有异常
     */
    @ExceptionHandler(value = Exception.class)
    public ModelAndView exception(Exception exception, WebRequest request) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("errorMessage",exception.getMessage());
        return modelAndView;
    }

    /**
     * 全局参数绑定
     * @param model
     */
    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("msg","额外信息");
    }

    @InitBinder
    public  void  initBinder(WebDataBinder webDataBinder) {
        //设置忽略id属性
        webDataBinder.setDisallowedFields("id");
    }

}
