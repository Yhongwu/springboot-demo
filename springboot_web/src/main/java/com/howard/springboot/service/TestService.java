package com.howard.springboot.service;

import com.github.pagehelper.Page;
import com.howard.springboot.domain.Test;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TestService {
    Test findByName(@Param("name") String name);

    int insert(Test user);

    Page<Test> findByPage(int pageNo, int PageSize);

    void save(Test test);

    Test get(String id);

    Test findById(Integer id);

    List<Test> findAll();
}
