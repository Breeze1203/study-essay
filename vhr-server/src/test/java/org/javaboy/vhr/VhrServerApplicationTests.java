package org.javaboy.vhr;

import org.javaboy.vhr.bean.Hr;
import org.javaboy.vhr.mapper.HrMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class VhrServerApplicationTests {

    @Autowired
    HrMapper hrMapper;

    @Test
    void contextLoads() {
        Hr admin = hrMapper.findHrByUsername("admin");
        System.out.println(admin);
    }

}
