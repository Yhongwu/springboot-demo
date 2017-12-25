package com.howard.springboot03.service;

import com.howard.springboot03.domain.Person;

public interface TransactionService {
    public Person savePersonWithRollBack(Person person);
    public Person savePersonWithoutRollBack(Person person);
}
