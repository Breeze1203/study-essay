package org.javaboy.vhr;


import org.javaboy.vhr.bean.Salary;
import org.javaboy.vhr.mapper.SalaryMapper;
import org.javaboy.vhr.rocketmq.ProducerMailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


/**
 * Unit test for simple VhrServerApplication.
 */
@SpringBootTest
public class VhrServerApplicationText{
    @Autowired
    SalaryMapper salaryMapper;

    @Autowired
    ProducerMailService producerMailService;

    @Test
    void contextLoads() {
        List<Salary> salaries = salaryMapper.selectAll();
        for (Salary s:salaries) {
            System.out.println(s.toString());
        }
    }
}
