package org.example.message;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

public class SyncConsumer {
    public static void main(String[] args) throws MQClientException {
        //构建消费者实例，相同的组名的多个消费者实例，搭建消费集群
        DefaultMQPushConsumer group1 = new DefaultMQPushConsumer("group1");
        group1.setNamesrvAddr("192.168.133.143:9876");
        // 接收特定主题的消息
        group1.subscribe("Sync_Test","*");
        group1.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                for (MessageExt msg:list){
                    //
                    System.out.printf(Thread.currentThread().getName() + "接收的是同步消息: " + new String(msg.getBody()) + "%n");
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        group1.start();
    }
}
