package com.howard.springboot02;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * springboot对web的支持
 * 使用thymeleaf模板
 * 支持jsp等其它 但目前内嵌的tomcat不支持以jar形式运行jsp
 */
@Controller
@SpringBootApplication
public class SpringbootThymeleafApplication {

	@RequestMapping("/")
	public String index(Model model) {
		Person single = new Person("aa",20);

		List<Person> personList = new ArrayList<>();
		Person p1 = new Person("bb",21);
		Person p2 = new Person("cc",22);
		Person p3 = new Person("dd",23);

		personList.add(p1);
		personList.add(p2);
		personList.add(p3);

		model.addAttribute("singlePerson",single);
		model.addAttribute("people",personList);

		return "index";
	}

	@RequestMapping("/test")
	@ResponseBody
	public String test() {
		return "test filter";
	}
	/*************************springmvc4下java配置方式thymeleaf***************/
	/**
	 *
	 * SpringTemplateEngine ：驱动springmvc下使用Thymeleaf模板引擎
	 * TemplateResolver：设置通用模板 前缀后缀等
	 */
/*	@Bean
	public TemplateResolver templateResolver() {
		TemplateResolver templateResolver = new ServletContextTemplateResolver();
		templateResolver.setPrefix("/WEB-INF/templates");
		templateResolver.setSuffix(".html");
		templateResolver.setTemplateMode("HTML5");
		return templateResolver;
	}

	@Bean
	public SpringTemplateEngine templateEngine() {
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.setTemplateResolver(templateResolver());
		return templateEngine;
	}

	@Bean
	public ThymeleafViewResolver thymeleafViewResolver() {
		ThymeleafViewResolver thymeleafViewResolver = new ThymeleafViewResolver();
		thymeleafViewResolver.setTemplateEngine(templateEngine());
		//默认配置的就是这个 无需自己重新配置
		//thymeleafViewResolver.setViewClass(ThymeleafView.class);
		return thymeleafViewResolver;
	}*/
	/************springboot下对Thymeleaf进行了自动配置 所以不需要上面那么多配置****/
	/**
	 * springframewospring-boot-autoconfigure-1.5.9.RELEASE.jar/org.pringframework.boot.autoconfigure.thymeleaf
	 * 该包下ThymeleafAutoConfiguration、ThymeleafProperties、ThymeleafTemplateAvailabilityProvider对Thymeleaf做了默认配置
	 * 默认前缀：classpath:/templates/
	 * 默认后缀：.html
	 * 默认编码：UTF-8
	 * 默认媒体类型：text/html
	 * 默认模式：HTML5
	 * 默认模板换成：开启 （开发过程应该关闭）
	 *
	 * 上面这些配置可在ThymeleafProperties查看
	 * 可在application.properties使用spring.thymeleaf.xxx修改
	 *
	 */

	/**
	 * springboot的web默认已经做好了许多配置 可见autoconfigure下的org.pringframework.boot.autoconfigure.web下的WebMvcAutoConfiguration、WebMvcProperties
	 * 自动配置将静态资源放置于目录类路径下/static、/public、/resouces、/META-INF/resources
	 * web下的WebMvcAutoConfiguration默认将/映射到首页index.html 可分别位于以上的静态资源目录
	 */

	public static void main(String[] args) {
		SpringApplication.run(SpringbootThymeleafApplication.class, args);
	}
}
