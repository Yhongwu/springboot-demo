package com.howard.springboot_exception;

import com.howard.springboot_exception.exception.MyException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 同一异常处理
 * 只测试GlobalExceptionHandler通过
 * 其余测试暂未成功
 */
//@ComponentScan(basePackages = "com.howard.springboot_exception.controller")
@SpringBootApplication
public class SpringbootExceptionApplication {


	public static void main(String[] args) {
		SpringApplication.run(SpringbootExceptionApplication.class, args);
	}
}
