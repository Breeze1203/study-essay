package org.javaboy.vhr.rocketmq;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @projectName: vhr
 * @package: org.javaboy.vhr.rocketmq
 * @className: ProducerConfiguration
 * @author: pt3548297839
 * @description: TODO
 * @date: 2023/9/23 11:36
 * @version: 1.0
 */
@Configuration
public class ProducerConfiguration {
    @Bean
    public DefaultMQProducer defaultMQProducer() {
        DefaultMQProducer producer = new DefaultMQProducer();
        producer.setProducerGroup("mail-push");
        producer.setNamesrvAddr("192.168.3.54:9876");
        return producer;
    }
}
