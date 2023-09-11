package org.example.scheduled;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @projectName: rocketmq
 * @package: org.example.scheduled
 * @className: ScheduledMessageConsumer
 * @author: pt3548297839
 * @description: 延时消息
 * @date: 2023/9/6 7:41
 * @version: 1.0
 */

public class ScheduledMessageConsumer {
    public static void main(String[] args) throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("ScheduledConsumer_Group");
        consumer.setNamesrvAddr("192.168.3.54:9876");
        consumer.subscribe("Scheduled_Test","*");
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                for (MessageExt message : list) {
                    // 打印延迟的时间 System.currentTimeMillis()获取当前时间戳
                    // message.getStoreTimestamp() 获取消息在broker存储的时间戳
                    byte[] body = message.getBody();
                    String s = new String(body);
                    System.out.println("Receive message[msgId=" + message.getMsgId() + "内容+"+s+"] "
                            + (System.currentTimeMillis() - message.getStoreTimestamp()) + "ms later");
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        consumer.start();
    }
}
