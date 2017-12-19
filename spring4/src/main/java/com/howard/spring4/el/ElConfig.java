package com.howard.spring4.el;

import com.sun.javafx.runtime.SystemProperties;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;

/**
 * 常用el表达式
 * 注意#和$
 */
@Configuration
@ComponentScan("com.howard.spring4.el")
@PropertySource("classpath:com/howard/spring4/el/test.properties")  //指定属性文件的位置,必须在下面注入一个bean 属性文件默认读取resource下的
public class ElConfig {
    //注入普通字符串
    @Value("Hello World !")
    private String normal;

    //注入操作系统属性
    @Value("#{systemProperties['os.name']}")
    private String osName;

    //注入表达式结果
    @Value("#{T(java.lang.Math).random() * 100.0}")
    private double randomNumber;

    //注入其它bean属性
    @Value("#{demoService.another}")
    private String fromAnother;

    //注入文件资源
    @Value("classpath:com/howard/spring4/el/test.txt")
    private Resource testFile;

    //注入网址资源
    @Value("http://www.baidu.com")
    private Resource testUrl;

    //注入配置文件
    @Value("${test.author}")
    private String testName;

    //注入配置文件
    @Autowired
    private Environment environment;

    /**
     * @PropertySource指定文件路径 若使用@value注入 则必须配置PropertySourcesPlaceholderConfigurer
     * @return
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    public void outputResource() {
        try {
            System.out.println(normal);
            System.out.println(osName);
            System.out.println(randomNumber);
            System.out.println(fromAnother);

            System.out.println(IOUtils.toString(testFile.getInputStream())); //需引入common-io
            System.out.println(IOUtils.toString(testUrl.getInputStream()));
            System.out.println(testName);
            System.out.println(environment.getProperty("test.author"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
