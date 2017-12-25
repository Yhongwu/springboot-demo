package com.howard.springboot03.service.impl;

import com.howard.springboot03.dao.PersonRepository;
import com.howard.springboot03.domain.Person;
import com.howard.springboot03.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class CacheServiceImpl implements CacheService{

    @Autowired
    private PersonRepository personRepository;

    /**
     * 建立缓存
     * #person.id 表示缓存keyperson的id属性 缓存名称为people
     * @param person
     * @return
     */
    @Override
    @CachePut(value = "people",key = "#person.id")
    public Person save(Person person) {
        Person p = personRepository.save(person);
        System.out.println("缓存id："+p.getId());
        return p;
    }

    /**
     * @CacheEvict 从缓存people中删除key为id的缓存
     * @param id
     */
    @Override
    @CacheEvict(value = "people")
    public void remove(Long id) {
        System.out.println("移除缓存id："+id);
    }

    /**
     * 缓存key为id到缓存名称为people中
     * @param person
     * @return
     */
    @Override
    @Cacheable(value = "people",key = "#person.id")
    public Person findOne(Person person) {
        Person p = personRepository.findOne(person.getId());
        System.out.println("设置缓存id："+p.getId());
        return p;
    }
}
