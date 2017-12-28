package com.howard.springboot05;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * springboot集成spring security
 */
@SpringBootApplication
public class SpringbootSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootSecurityApplication.class, args);
	}
}
/**
 * springboot对spring security的支持：
 * org.springframework.boot.autoconfigure.security
 * 主要通过SecurityAutoConfiguration和SecurityProperties完成，导入了SpringBootWebSecurityConfiguration
 * 可看到：默认忽略了"/css/**", "/js/**","/images/**", "/webjars/**", "favicon.ic静态资源的拦截
 * 自动配置了一个内存中的用户user 密码在启动才决定
 * 自动配置了SecurityFilterChain的bean
 * 在spring中需要用@EnableWebSecurity开启支持
 */
