package org.javaboy.vhr;


import org.javaboy.vhr.rocketmq.ProducerMailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


/**
 * Unit test for simple VhrServerApplication.
 */
@SpringBootTest
public class VhrServerApplicationText{
    @Autowired
    ProducerMailService producerMailService;

    @Test
    void contextLoads() {
//        producerMailService.send();
    }
}
