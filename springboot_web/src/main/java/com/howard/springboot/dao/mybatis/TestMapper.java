package com.howard.springboot.dao.mybatis;

import com.github.pagehelper.Page;
import com.howard.springboot.domain.Test;
import org.apache.ibatis.annotations.Param;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by hongwu on 2018/1/4.
 */
@Repository
public interface TestMapper {
    //@Select("select * from user where name = #{name}")
    Test findByName(@Param("name") String name);

    //@Insert("insert into user (id,name,age) values (#{id},#{name},#{age})")
    int insert(Test user);

    /**
     * 注意Page必须使用com.github.pagehelper.Page
     * @return
     */
    Page<Test> findByPage();

    Test findById(Integer id);

    List<Test> findAll();
}
