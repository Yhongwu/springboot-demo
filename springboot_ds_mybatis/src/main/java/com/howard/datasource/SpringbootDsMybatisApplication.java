package com.howard.datasource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * springboot-mybatis多数据源的配置
 * 基于aop的注解实现切换数据源
 */
@SpringBootApplication
public class SpringbootDsMybatisApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootDsMybatisApplication.class, args);
	}
}
