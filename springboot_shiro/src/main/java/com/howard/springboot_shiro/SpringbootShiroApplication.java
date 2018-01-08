package com.howard.springboot_shiro;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * springboot整合shiro
 * 包括配置方式 注解方式 自定义过滤器 ehcache缓存 明文密文加密 记住我
 */
@SpringBootApplication
@MapperScan("com.howard.springboot_shiro.mapper")
public class SpringbootShiroApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootShiroApplication.class, args);
	}
}
