package com.howard.spring4.fortest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * 测试代码见测试目录
 * Created by hongwu on 2017/12/19.
 */
@Configuration
public class TestConfig {

    //不同环境实例化不同bean
    @Bean
    @Profile("dev")
    public TestBean devTestBean() {
        return new TestBean("dev");
    }

    @Bean
    @Profile("prod")
    public TestBean prodTestBean() {
        return new TestBean("prod");
    }
}
