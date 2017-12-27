package com.howard.springboot07;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Springboot07Application {

	public static void main(String[] args) {
		SpringApplication.run(Springboot07Application.class, args);
	}
}
/**
 * springboot集成Rabbit
 * 启动程序后看到控制台输出：接收到的消息：Rabbit测试消息
 * 管理界面：http://192.168.124.128:15672 默认账户 guest/guest
 */