package com.howard.springboot03.service.impl;

import com.howard.springboot03.dao.PersonRepository;
import com.howard.springboot03.domain.Person;
import com.howard.springboot03.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionServiceImpl implements TransactionService{

    @Autowired
    private PersonRepository personRepository;

    @Override
    @Transactional(rollbackFor = {IllegalArgumentException.class},readOnly = false) //指定异常回滚
    public Person savePersonWithRollBack(Person person) {
        Person p = personRepository.save(person);
        if (person.getName().equals("hh")) {
            throw new IllegalArgumentException("hh已经存在，数据回滚");
        }
        return p;
    }

    @Override
    @Transactional(noRollbackFor = {IllegalArgumentException.class},readOnly = false) //指定异常不回滚
    public Person savePersonWithoutRollBack(Person person) {
        Person p = personRepository.save(person);
        if (person.getName().equals("hh")) {
            throw new IllegalArgumentException("hh已经存在,数据不回滚");
        }
        return p;
    }
}
