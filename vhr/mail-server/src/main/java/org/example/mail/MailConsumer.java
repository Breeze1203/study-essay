package org.example.mail;

import com.alibaba.fastjson.JSON;
import jakarta.annotation.Resource;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
    MailService mailService;

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
                    User user = JSON.parseObject(s, User.class);
                    mailSend.sentThymeleafHtml(user);
                }
                return ConsumeConcurrentlyStatus.RECONSUME_LATER;
            }
        });
        try {
            defaultMQPushConsumer.start();
        } catch (MQClientException e) {
            throw new RuntimeException(e);
        }
    }
}
