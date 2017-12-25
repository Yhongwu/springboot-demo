package com.howard.springboot04;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * springboot对spring data rest的自动配置位于autoconfigure下data.rest下
 * 可用postman工具进行测试
 *
 *
 */
@SpringBootApplication
public class Springboot04Application {

	public static void main(String[] args) {
		SpringApplication.run(Springboot04Application.class, args);
	}
}

/*****************测试***********************/
/**
 获取全部：get
 http://127.0.0.1:8080/people
 	按自己指定的字段返回：http://127.0.0.1:8080/people?projection=list   （见MyPerson）
 	按自己指定的字段返回：http://127.0.0.1:8080/people?projection=virtual  （见MyVirtualPerson）
 根据主键获取：get
 http://127.0.0.1:8080/people/1
 	按自己指定的字段返回：http://127.0.0.1:8080/people/1?projection=list   （见MyPerson）
 分页：get  page=1：第二页
 http://127.0.0.1:8080/people/?page=1&size=2
 排序：get
 http://127.0.0.1:8080/people/?sort=age,desc
 新增：post
 http://127.0.0.1:8080/people
   请求体设置json：{"name":"mm","address":"成都","age":20}
 更新：put 注意id为待更新记录的id
 http://127.0.0.1:8080/people/7
   请求体设置json：{"name":"nn","address":"成都","age":20}
 删除：delete 注意id为待删除的id （可在方法上加@RestResource(exported = false)屏蔽）
 http://127.0.0.1:8080/people/7
 自定义查询： get  注意有个search
 http://127.0.0.1:8080/people/search/name?name=bb
 */