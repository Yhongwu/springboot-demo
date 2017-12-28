package com.howard.springboot03.service;

import com.howard.springboot03.domain.Person;

public interface CacheService {
    public Person save(Person person);
    public void remove(Long id);
    public Person findOne(Person person);
}
