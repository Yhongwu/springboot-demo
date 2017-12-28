package com.howard.springboot04.domain;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

/**
 * 按自己希望的返回组合的json
 * {"fullInfo": "hh 22"}
 */
@Projection(name = "virtual",types = Person.class)
public interface MyVirtualPerson {
    /**
     * 相应的属性一定要有getter方法
     * @return
     */
    @Value("#{target.name} #{target.age}")
    String getFullInfo();
}
