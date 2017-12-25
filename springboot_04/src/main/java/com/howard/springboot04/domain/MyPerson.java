package com.howard.springboot04.domain;

import org.springframework.data.rest.core.config.Projection;

/**
 * 按自己希望的字段返回json
 */
@Projection(name = "list",types = Person.class)
public interface MyPerson {
    Integer getAge();
    String getName();
}
