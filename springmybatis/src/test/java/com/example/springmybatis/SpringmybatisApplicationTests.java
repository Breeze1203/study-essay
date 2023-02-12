package com.example.springmybatis;

import com.example.springmybatis.mapper.UserMapper;
import com.example.springmybatis.model.User;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringmybatisApplicationTests {

    @Resource(name = "student")
    UserMapper userMapper;

    @Test
    void contextLoads() {
        User user = new User();
        user.setPassword("python");
        user.setPassword("1245");
        user.setRole("user");
        int result = userMapper.insertUser(user);
        System.out.println(result);
    }

    @Test
    void text_1() {
        String php = "php";
        User user = userMapper.getUserByUsername(php);
        System.out.println(user);
    }

}
