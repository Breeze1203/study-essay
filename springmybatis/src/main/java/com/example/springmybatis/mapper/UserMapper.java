package com.example.springmybatis.mapper;


import org.apache.ibatis.annotations.Mapper;
import com.example.springmybatis.model.User;
import org.springframework.stereotype.Repository;


@Mapper
@Repository("student")
public interface UserMapper {

    public int insertUser(User user);
    public User getUserByUsername(String username);
}
