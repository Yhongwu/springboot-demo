package com.howard.springboot.service.Impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.howard.springboot.dao.mybatis.TestMapper;
import com.howard.springboot.dao.redis.TestDao;
import com.howard.springboot.domain.Test;
import com.howard.springboot.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
//@Transactional
public class TestServiceImpl implements TestService {

    @Autowired
    private TestMapper testMapper;
    @Autowired
    private TestDao testDao;

    @Override
    public Test findByName(String name) {
        return testMapper.findByName(name);
    }
    //自己手动实现缓存的方式
    @Override
    public Test findById(Integer id) {
        Test test = new Test();
        test = testDao.get(id + "");
        if (test == null) {
            test = testMapper.findById(id);
            testDao.save(test);
        }
        return test;
    }

    @Override
    public int insert(Test user) {
        int result = testMapper.insert(user);
        int i = 2/0;
        return result;
    }

    @Override
    public Page<Test> findByPage(int pageNo, int PageSize) {
        PageHelper.startPage(pageNo,PageSize);
        return testMapper.findByPage();
    }

    @Override
    public void save(Test test) {
         testDao.save(test);
    }

    @Override
    public Test get(String id) {
        return testDao.get(id);
    }

    @Override
    public List<Test> findAll() {
        return testMapper.findAll();
    }
}
