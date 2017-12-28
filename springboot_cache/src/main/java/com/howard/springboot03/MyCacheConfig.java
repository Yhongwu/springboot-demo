package com.howard.springboot03;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

/**
 * 效果等同在application.properties和Springboot03Application加@EnableCaching
 */
/*@Configuration
@EnableCaching //指定缓存支持
public class MyCacheConfig {

    //可在此配置具体哪种缓存的相关配置
}*/
