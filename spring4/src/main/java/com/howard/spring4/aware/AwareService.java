package com.howard.spring4.aware;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 *
 * spring提供的aware接口：
 * BeanNameAware，ResourceLoaderAware，BeanFactoryAware，MessageSourceAware，ApplicationEventPublisherAware，ApplicationContextAware
 * Created by hongwu on 2017/12/19.
 */
@Component
public class AwareService implements BeanNameAware,ResourceLoaderAware{
    
    private String beanName;
    
    private ResourceLoader loader;
    
    @Override
    public void setBeanName(String s) {
        this.beanName = s;
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.loader = resourceLoader;

    }
    
    public void outputResult() {
        System.out.println("Bean的名称："+beanName);

        Resource resource = loader.getResource("classpath:com/howard/spring4/aware/test.txt");
        try {
            System.out.println("ResourceLoader加载的文件内容为:" + IOUtils.toString(resource.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
