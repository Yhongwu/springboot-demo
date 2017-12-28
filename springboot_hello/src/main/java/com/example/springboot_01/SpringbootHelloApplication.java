package com.example.springboot_01;

import com.example.springboot_01.config.StudentSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @SpringBootApplication : 组合注解，包含 @SpringBootConfiguration @EnableAutoConfiguration @ComponentScan
 * 其中：@EnableAutoConfiguration的源码里导入的@Import(EnableAutoConfigurationImportSelector.class)能够扫描
 * META-INF/spring.factories，即spring-boot-autoconfigure-1.5.9.RELEASE.jar下的META-INF/spring.factories，能为我们根据条件默认注入bean(类似例子见spring4的conditional)
 * 每一个注解实际上跟条件注入bean差不多，如package org.springframework.boot.autoconfigure.condition下的注解@ConditionalOnWebApplication
 * 实际上是在满足条件OnWebApplicationCondition下才默认注册该bean
 */
@RestController
@SpringBootApplication
@EnableConfigurationProperties //激活配置类 可以使用@PropertySource注解 见StudentSettings
public class SpringbootHelloApplication {


	/**
	 * springboot可以直接使用@Value加载application.properties(默认)中配置的属性
	 * spring则需要@PropertySource引入
	 */
	@Value("${book.name}")
	private String bookName;

	@Autowired
	private StudentSettings studentSettings;

	@RequestMapping("/")
	public String index() {
		return "book name:" + bookName;
		//return "Student: " + studentSettings.getName() + " - " + studentSettings.getAge();
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringbootHelloApplication.class, args);
	}
}
