package org.example.mail;

import com.alibaba.fastjson.JSON;
import jakarta.annotation.Resource;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;

import org.example.bean.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.List;

/**
 * @projectName: vhr
 * @package: org.example.mail
 * @className: MailConsumer
 * @author: pt3548297839
 * @description: TODO
 * @date: 2023/9/23 10:57
 * @version: 1.0
 */
@Component
public class MailConsumer {

    @Autowired
    MailSend mailSend;
    @Autowired
    DefaultMQPushConsumer defaultMQPushConsumer;

    public void receive() {
        defaultMQPushConsumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                for (MessageExt m : list) {
                    String s = new String(m.getBody());
                    Employee employee= JSON.parseObject(s, Employee.class);
                    mailSend.sentThymeleafHtml(employee);
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        try {
            defaultMQPushConsumer.start();
        } catch (MQClientException e) {
            throw new RuntimeException(e);
        }
    }
}
