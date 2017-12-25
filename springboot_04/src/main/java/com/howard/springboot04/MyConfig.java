package com.howard.springboot04;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

/**
 * spring data rest的基础配置
 * 等效于在application.properties配置
 */
@Configuration
public class MyConfig {
    /*
    @Bean
    public RepositoryRestConfigurer repositoryRestResource() {
        return new RepositoryRestConfigurerAdapter(){
            @Override
            public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
                config.setBasePath("/api");
            }
        };
    }
    */
}
