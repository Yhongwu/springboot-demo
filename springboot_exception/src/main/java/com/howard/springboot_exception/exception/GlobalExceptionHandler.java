package com.howard.springboot_exception.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理
 * 使用@ControllerAdvice
 * 无法根据不同的头部返回不同的数据格式，而且无法针对404、403等多种状态进行处理
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    public static final String DEFAULT_ERROR_VIEW = "myerror";
    @ExceptionHandler(value = MyException.class)
    @ResponseBody
    public ResponseEntity defaultErrorHandler(HttpServletRequest req, MyException e) throws Exception {
        return ResponseEntity.ok("ok");
    }
    @ExceptionHandler(value = Exception.class)
    public String defaultErrorHandler(HttpServletRequest req, Exception e, Model model) throws Exception {
        model.addAttribute("exception",e);
        model.addAttribute("url",req.getRequestURL());
        return DEFAULT_ERROR_VIEW;
//        ModelAndView mav = new ModelAndView();
//        mav.addObject("exception", e);
//        mav.addObject("url", req.getRequestURL());
//        mav.setViewName(DEFAULT_ERROR_VIEW);
//        return mav;
    }
}
