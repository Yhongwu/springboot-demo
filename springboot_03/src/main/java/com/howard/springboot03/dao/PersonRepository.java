package com.howard.springboot03.dao;

import com.howard.springboot03.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
public interface PersonRepository extends JpaRepository<Person,Long>{

    //使用规范方法名查询
    List<Person> findByAddress(String name);

    Person findByNameAndAddress(String name,String address);

    //使用@Query里定义的sql语句查询 参数按照名称绑定
    @Query("select p from Person p where  p.name= :name and p.address= :address")
    Person withNameAndAddressQuery(@Param("name") String name,@Param("address") String address);

    //使用@NamedQuery查询 在实体类中定义
    Person withNameAndAddressNamedQuery(String name,String address);
}
