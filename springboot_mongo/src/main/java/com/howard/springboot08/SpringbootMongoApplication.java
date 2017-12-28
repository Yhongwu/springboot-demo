package com.howard.springboot08;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * springboot 集成 mongodb
 * spring data mongodb
 * 默认已经配置了相关bean
 * 默认注解了@EnableMongoRepositories 可查看autoconfigure
 */
@SpringBootApplication
public class SpringbootMongoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootMongoApplication.class, args);
	}
}
