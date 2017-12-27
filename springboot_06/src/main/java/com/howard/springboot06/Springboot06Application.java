package com.howard.springboot06;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * springboot集成activemq
 * activemq遵循jms规范
 */
@SpringBootApplication
public class Springboot06Application {

	public static void main(String[] args) {
		SpringApplication.run(Springboot06Application.class, args);
	}
}

/**
 * 运行后看到控制台输出：接受消息：activemq测试消息
 * http://192.168.124.128:8161 可查看activemq管理界面 默认账号密码：admin/admin
 */