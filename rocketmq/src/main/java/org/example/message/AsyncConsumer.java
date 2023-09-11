package org.example.message;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * @projectName: rocketmq
 * @package: org.example.message
 * @className: AsyncConsumer
 * @author: pt3548297839
 * @description: 异步发送消息的生产者实列
 * @date: 2023/9/9 17:10
 * @version: 1.0
 */

public class AsyncConsumer {
    public static void main(String[] args) throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("Async_grop");
        consumer.setNamesrvAddr("192.168.133.143:9876");
        consumer.subscribe("Async","TagA");
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            // 第一个参数为接收到的消息列表  第二个参数包含了包含了一些与消息消费相关的上下文信息，例如消息的重试次数、消息队列的信息等
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                for (int i = 0; i < list.size(); i++) {
                    System.out.println(list.get(i).getBody().toString());
                    System.out.println(list.get(i).getBornHost());
                }
//                CONSUME_SUCCESS：消费成功，表明消费者已经成功处理并消费了消息。
//                RECONSUME_LATER：稍后重试，表明消费者遇到了一些问题，需要稍后重试消费当前消息。
//                SUSPEND_CURRENT_QUEUE_A_MOMENT：暂停当前队列一段时间，表明消费者希望暂停当前消息队列的消费一段时间
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        consumer.start();
    }
}
