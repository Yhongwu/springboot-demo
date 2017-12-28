package com.howard.springboot08.controller;

import com.howard.springboot08.dao.PersonRepository;
import com.howard.springboot08.domain.Location;
import com.howard.springboot08.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;

@RestController
public class MongoController {
    @Autowired
    PersonRepository personRepository;

    @RequestMapping("/save")
    public Person save() {
        Person p = new Person("howard",32);
        Collection<Location> locations = new LinkedHashSet<>();
        Location loc1 = new Location("广州","2016");
        Location loc2 = new Location("深圳","2017");
        Location loc3 = new Location("广州","2018");
        locations.add(loc1);
        locations.add(loc2);
        locations.add(loc3);
        p.setLocations(locations);
        return personRepository.save(p);
    }

    @RequestMapping("/query1")
    public Person query1(String name) {
        return personRepository.findByName(name);
    }

    @RequestMapping("/query2")
    public List<Person> query2(Integer age) {
        return personRepository.withQueryFindByAge(age);
    }
}
