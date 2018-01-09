package com.howard.springboot_jsp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * springboot整合jsp
 * 注意：使用maven命令：springboot:run启动项目
 * 继承SpringBootServletInitializer
 */
@SpringBootApplication
public class SpringbootJspApplication extends SpringBootServletInitializer{
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SpringbootJspApplication.class);
	}
	public static void main(String[] args) {
		SpringApplication.run(SpringbootJspApplication.class, args);
	}
}
