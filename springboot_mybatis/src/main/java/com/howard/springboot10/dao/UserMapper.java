package com.howard.springboot10.dao;

import com.howard.springboot10.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 注解方式
 */
//@Mapper
public interface UserMapper {

    //@Select("select * from user where name = #{name}")
    User findByName(@Param("name") String name);

    //@Insert("insert into user (id,name,age) values (#{id},#{name},#{age})")
    int insert(User user);
}
