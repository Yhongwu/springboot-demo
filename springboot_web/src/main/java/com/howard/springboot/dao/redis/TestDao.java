package com.howard.springboot.dao.redis;

import com.howard.springboot.domain.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
@Repository
public class TestDao {
    @Autowired
    RedisTemplate<Object,Object> redisTemplate;

    @Resource(name="redisTemplate")
    ValueOperations<String,Object> valOps;

    public void save(Test test){
        valOps.set(test.getId()+"", test);
    }

    public Test get(String id){
        return (Test) valOps.get(id);
    }
}
