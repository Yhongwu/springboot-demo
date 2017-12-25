package com.howard.springboot03.web;

import com.howard.springboot03.domain.Person;
import com.howard.springboot03.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @RequestMapping("/rollback")
    public Person rollback() {
        Person p = new Person();
        p.setAddress("xxxx");
        p.setAge(11);
        p.setName("hh");
        return transactionService.savePersonWithRollBack(p);
    }

    @RequestMapping("/norollback")
    public Person noRollback() {
        Person p = new Person();
        p.setAddress("xxxx");
        p.setAge(11);
        p.setName("hh");
        return transactionService.savePersonWithoutRollBack(p);
    }
}
