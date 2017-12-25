package com.howard.springboot03.web;

import com.howard.springboot03.dao.PersonRepository;
import com.howard.springboot03.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DataController {

    //spring data jpa 自动注册了repository bean 可直接注入
    @Autowired
    PersonRepository personRepository;

    @RequestMapping("/save")
    public Person save(String name,String address,Integer age) {
        Person p = personRepository.save(new Person(name,address,age));
        return p;
    }

    @RequestMapping("/query1")
    public List<Person>query1(String address) {
        List<Person> person = personRepository.findByAddress(address);
        return person;
    }

    @RequestMapping("/query2")
    public Person query2(String name,String address) {
        Person person = personRepository.findByNameAndAddress(name, address);
        return person;
    }

    @RequestMapping("/query3")
    public Person query3(String name,String address) {
        Person p = personRepository.withNameAndAddressQuery(name,address);
        return  p;
    }

    @RequestMapping("/query4")
    public Person query4(String name,String address){
        Person p = personRepository.withNameAndAddressNamedQuery(name,address);
        return p;
    }

    /**
     * 测试排序
     * @return
     */
    @RequestMapping("/sort")
    public List<Person> sort() {
        List<Person> person = personRepository.findAll(new Sort(Sort.Direction.ASC, "age"));
        return person;
    }

    @RequestMapping("/page")
    public Page<Person> page() {
        Page<Person> person = personRepository.findAll(new PageRequest(1, 2));
        return  person;
    }
}
