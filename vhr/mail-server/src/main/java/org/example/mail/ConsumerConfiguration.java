package org.example.mail;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.exception.MQClientException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @projectName: vhr
 * @package: org.example.mail
 * @className: ConsumerConfiguration
 * @author: pt3548297839
 * @description: TODO
 * @date: 2023/9/23 11:43
 * @version: 1.0
 */
@Configuration
public class ConsumerConfiguration {
    @Bean
    public DefaultMQPushConsumer defaultMQPushConsumer(){
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer();
        consumer.setNamesrvAddr("192.168.3.54:9876");
        consumer.setConsumerGroup("mail-receive");
        try {
            consumer.subscribe("jobMail", "*");
        } catch (MQClientException e) {
            throw new RuntimeException(e);
        }
        return consumer;
    }
}
