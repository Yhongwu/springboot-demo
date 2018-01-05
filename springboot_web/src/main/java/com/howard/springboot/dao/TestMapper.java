package com.howard.springboot.dao;

import com.howard.springboot.domain.Test;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by hongwu on 2018/1/4.
 */
@Repository
public interface TestMapper {
    //@Select("select * from user where name = #{name}")
    Test findByName(@Param("name") String name);

    //@Insert("insert into user (id,name,age) values (#{id},#{name},#{age})")
    int insert(Test user);
}
