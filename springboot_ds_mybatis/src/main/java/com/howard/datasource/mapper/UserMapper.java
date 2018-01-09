package com.howard.datasource.mapper;

import com.howard.datasource.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    int insert(User user);
    User findByName(String username);

}
