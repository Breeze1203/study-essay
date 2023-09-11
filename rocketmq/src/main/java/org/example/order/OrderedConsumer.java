package org.example.order;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

public class OrderedConsumer {
    public static void main(String[] args) throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("Order_Group");
//        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        consumer.setNamesrvAddr("192.168.133.143:9876");
        consumer.subscribe("Order_Test", "*");
        consumer.registerMessageListener(new MessageListenerOrderly() {
            @Override
            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs,
                                                       ConsumeOrderlyContext context) {
                //观察是否有序，线程是否是同一个，每个队列(订单)是同一个消息
                for (MessageExt me : msgs) {
                    System.out.printf(Thread.currentThread().getName() + " 订单消息: " + new String(me.getBody()) + "%n");
                }
                return ConsumeOrderlyStatus.SUCCESS;

            }
        });
        consumer.start();
        System.out.printf("Consumer Started.%n");
    }
}
