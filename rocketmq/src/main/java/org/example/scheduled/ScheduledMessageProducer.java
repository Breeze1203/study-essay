package org.example.scheduled;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;

/**
 * @projectName: rocketmq
 * @package: org.example.scheduled
 * @className: ScheduledMessageProducer
 * @author: pt3548297839
 * @description: 延迟消息
 * @date: 2023/9/6 7:43
 * @version: 1.0
 */
/*
实现思路：将延迟消息通过一个临时存储进行暂存，到期后才投递到目标的Topic中
核心思路：所有的延迟消息由producer发出后，都会存放到同一个topic中，根据延迟消息lever的个数
创建对应数量的队列，也就是18个level对应了18个队列
 */
public class ScheduledMessageProducer {
    public static void main(String[] args) throws MQClientException, UnsupportedEncodingException, MQBrokerException, RemotingException, InterruptedException {
        DefaultMQProducer producer = new DefaultMQProducer("ScheduledProducer_Group");
        producer.setNamesrvAddr("192.168.3.54:9876");
        producer.start();
        for (int i = 0; i < 5; i++) {
            Message message = new Message("Scheduled_Test", ("Hello Scheduled Message" + i).getBytes());
            // 设置消息延迟级别
            message.setDelayTimeLevel(6);
            producer.send(message);
        }
        producer.shutdown();
    }
}
