package com.howard.springboot03;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication
@EnableJpaRepositories //开启jpa支持
@EnableCaching //开启缓存支持 也可以单独配置成一个配置类
public class Springboot03Application {

	public static void main(String[] args) {
		SpringApplication.run(Springboot03Application.class, args);
	}
}

/**
 * org.springframework.boot.autoconfigure.transaction.TransactionAutoConfiguration
 * 配置了事务 springboot无需显示配置@EnableTransactionManagement
 *
 *
 */
/**
 * 缓存：
 * 先http://127.0.0.1:8080/put保存一个新的person (假设id=11)此时缓存了数据
 * 再次访问http://127.0.0.1:8080/able id为之前保存的数据id11 此时发现控制台没有sql语句 从缓存直接取出
 * 再次访问http://127.0.0.1:8080/evict 删除id=11的缓存
 * 再次访问http://127.0.0.1:8080/able id为之前保存的数据id11 此时发现控制台有sql语句 并将结果进行缓存
 * 再次访问http://127.0.0.1:8080/able id为之前保存的数据id11 此时发现控制台没有sql语句 从缓存直接取出
 */