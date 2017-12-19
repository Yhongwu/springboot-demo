package com.howard.spring4.profile;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * 不同环境实例化不同的bean
 * 通过@Profile指定
 */
@Configuration
public class ProfileConfig {
    /**
     * 开发环境
     * @return
     */
    @Bean
    @Profile("dev")
    public DemoBean devDemoBean() {
        return new DemoBean("dev");
    }

    /**
     * 生产环境
     * @return
     */
    @Bean
    @Profile("prod")
    public DemoBean prodDemoBean() {
        return new DemoBean("prod");
    }

}
