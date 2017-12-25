package com.howard.springboot03.web;

import com.howard.springboot03.domain.Person;
import com.howard.springboot03.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CacheController {

    @Autowired
    private CacheService cacheService;

    /**
     * 测试 @CachePut
     * @return
     */
    @RequestMapping("/put")
    public Person save() {
        Person p = new Person();
        p.setName("jj");
        p.setAddress("深圳");
        p.setAge(25);
        cacheService.save(p);
        return p;
    }

    /**
     * 测试 @CacheEvict
     */
    @RequestMapping("/evict")
    public void evict() {
        cacheService.remove(11L);
    }

    /**
     * @Cacheable
     * @return
     */
    @RequestMapping("/able")
    public Person cacheable() {
        Person p = new Person();
        p.setId(11L);
        Person person = cacheService.findOne(p);
        return person;
    }
}
