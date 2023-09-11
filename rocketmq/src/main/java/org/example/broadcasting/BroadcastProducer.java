package org.example.broadcasting;


import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;


/**
 * @projectName: rocketmq
 * @package: org.example.broadcasting
 * @className: BroadcastingProducer
 * @author: pt3548297839
 * @description: 广播消息
 * @date: 2023/9/9 17:57
 * @version: 1.0
 */
public class BroadcastProducer {
    public static void main(String[] args) throws Exception{
        DefaultMQProducer producer = new DefaultMQProducer("Broadcast_Group");
        producer.setNamesrvAddr("192.168.3.54:9876");
        producer.start();
        for (int i = 0; i < 15; i++) {
            producer.send(new Message("Broadcast",("广播消息"+i).getBytes("UTF-8")));

        }
    }
}
