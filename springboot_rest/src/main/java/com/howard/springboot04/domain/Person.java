package com.howard.springboot04.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import java.io.Serializable;

@Entity //注明这是和数据库映射的实体
@NamedQuery(name = "Person.withNameAndAddressNamedQuery",
query = "select p from Person p where p.name=?1 and p.address=?2")
public class Person implements Serializable{
    @Id //主键
    @GeneratedValue //自增
    private Long id;

    private String name;

    private String address;

    //返回的json中忽略该数据
    @JsonIgnore
    private Integer age;

    public Person() {
    }

    public Person(String name, String address, Integer age) {
        this.name = name;
        this.address = address;
        this.age = age;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
