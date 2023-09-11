package org.example.broadcasting;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.protocol.heartbeat.MessageModel;

import java.util.List;

/**
 * @projectName: rocketmq
 * @package: org.example.broadcasting
 * @className: BroadcastConsumer
 * @author: pt3548297839
 * @description: TODO
 * @date: 2023/9/9 19:28
 * @version: 1.0
 */
public class BroadcastConsumer {
    public static void main(String[] args) throws MQClientException {
        DefaultMQPushConsumer BroadcastConsumer = new DefaultMQPushConsumer("BroadcastConsumer_Group");
        BroadcastConsumer.subscribe("Broadcast","*");
        BroadcastConsumer.setNamesrvAddr("192.168.3.54:9876");
/*       ConsumeFromWhere 是 RocketMQ 定义的一个枚举类型，表示消费者从哪个位置开始进行消息消费。常见的几个选项包括：
      CONSUME_FROM_LAST_OFFSET：从上次消费的位置开始消费。即消费者会根据自己上次消费的消息偏移量来继续消费后续的消息。
       CONSUME_FROM_FIRST_OFFSET：从队列的最初位置开始消费。即消费者会从消息队列中最早的消息开始消费，忽略之前的消费记录。
              CONSUME_FROM_TIMESTAMP：从指定时间戳开始消费。即消费者会从指定时间之后的消息开始消费。
 */
        BroadcastConsumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        //设置消费者模式，默认集群模式
        BroadcastConsumer.setMessageModel(MessageModel.BROADCASTING); //设置广播模式
        // MessageListenerConcurrently并发消费 MessageListenerOrderly有序消费
        BroadcastConsumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                for (int i = 0; i < list.size(); i++) {
                    System.out.println(list.get(i).getBody().toString());
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        BroadcastConsumer.start();
    }
}
