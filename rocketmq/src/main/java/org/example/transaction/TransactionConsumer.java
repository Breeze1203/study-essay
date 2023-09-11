package org.example.transaction;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

public class TransactionConsumer {

    public static void main(String[] args) throws InterruptedException, MQClientException {
        // 1.创建消费者consumer、制定消费者组名
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("GroupTransaction");
        // 2.指定nameserver地址
        consumer.setNamesrvAddr("localhost:9876");
        // 3.订阅主题Topic和Tag
        consumer.subscribe("TransactionTopic", "*");
        // 4.设置回调函数，处理消息
        consumer.registerMessageListener(new MessageListenerConcurrently() {

            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs,
                                                            ConsumeConcurrentlyContext context) {
                for(MessageExt msg:msgs){
                    String key = msg.getKeys();
                    System.out.printf("%s Receive New Messages: %s %n", Thread.currentThread().getName(), new String(msg.getBody()));

                }
//                return ConsumeConcurrentlyStatus.RECONSUME_LATER;
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });

        //5.启动消费者
        consumer.start();

        System.out.printf("Consumer Started.%n");
    }
}
