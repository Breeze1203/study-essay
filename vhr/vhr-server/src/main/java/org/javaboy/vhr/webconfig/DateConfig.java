package org.javaboy.vhr.webconfig;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * @projectName: vhr
 * @package: org.javaboy.vhr.webconfig
 * @className: DateConfig
 * @author: pt3548297839
 * @description: TODO
 * @date: 2023/9/25 19:39
 * @version: 1.0
 */
@Configuration
public class DateConfig {
    @Bean
    ObjectMapper objectMapper(){
        ObjectMapper mapper = new ObjectMapper();
        mapper.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        return mapper;
    }
}
