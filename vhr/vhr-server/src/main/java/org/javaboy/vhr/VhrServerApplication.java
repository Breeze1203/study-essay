package org.javaboy.vhr;

import org.javaboy.vhr.rocketmq.Producer;
import org.javaboy.vhr.rocketmq.User;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@MapperScan(basePackages = "org.javaboy.vhr.mapper")
public class VhrServerApplication
{
    public static void main( String[] args )
    {
        ConfigurableApplicationContext context = SpringApplication.run(VhrServerApplication.class, args);
    }
}
