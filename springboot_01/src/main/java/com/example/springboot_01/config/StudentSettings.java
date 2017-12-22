package com.example.springboot_01.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 类型安全的配置
 * @ConfigurationProperties ： prefix 注明属性前缀，如student即 student.name,student.age等
 * 默认加载application.properties如果application.properties不存在，则在@PropertySource引入的配置里寻找
 */
@Component //注册bean
@ConfigurationProperties(prefix = "student") //springboot1.5开始取消了locations属性 这里不能直接指定自定义的配置文件了 替代方案为下面的注解
@PropertySource(value = "classpath:student.properties") //需要在启动类加上@EnableConfigurationProperties激活配置类
public class StudentSettings {
    private String name;
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
