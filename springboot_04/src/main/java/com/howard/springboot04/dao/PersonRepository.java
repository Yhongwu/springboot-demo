package com.howard.springboot04.dao;

import com.howard.springboot04.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

/**
 * spring data rest 默认以实体名+s来访问该实体的rest资源
 * 如果需要自己指定 则需要使用@RepositoryRestResource
 */
@RepositoryRestResource(path = "people")
public interface PersonRepository extends JpaRepository<Person,Long> {

    /**
     * 屏蔽掉delete的rest访问
     * @param id
     */
    @RestResource(exported = false)
    @Override
    public void delete(Long id);

    /**
     * 自定义查询
     * path：路径：search/name
     * rel：参数
     * @param name
     * @return
     */
    @RestResource(path="name",rel="name")
    public Person findByName(@Param("name") String name);
}
