package com.howard.springboot08.dao;

import com.howard.springboot08.domain.Person;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface PersonRepository extends MongoRepository<Person,String> {

    /**
     * 支持方法名查询
     * @param name
     * @return
     */
    Person findByName(String name);


    /**
     * 支持自定义Query查询
     * 参数使用json数据字符串
     * @param age
     * @return
     */
    @Query("{'age':?0}")
    List<Person> withQueryFindByAge(Integer age);
}
