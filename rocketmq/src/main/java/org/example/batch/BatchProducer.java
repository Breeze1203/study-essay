package org.example.batch;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

import java.util.ArrayList;
import java.util.List;

/**
 * @projectName: rocketmq
 * @package: org.example.batch
 * @className: BatchProducer
 * @author: pt3548297839
 * @description: 批量消息生产者
 * @date: 2023/9/6 7:50
 * @version: 1.0
 */
public class BatchProducer {
    public static void main(String[] args) throws MQClientException {
        DefaultMQProducer batchProducer = new DefaultMQProducer("BatchProducer_Group");
        batchProducer.setNamesrvAddr("192.168.3.54:9876");
        batchProducer.start();
        String topic="BathProducer_Topic";
        // 消息的集合
        List<Message> messages=new ArrayList<>();
        messages.add(new Message(topic,"TagA","OrderID001","Hello Word 0".getBytes()));
        messages.add(new Message(topic,"TagA","OrderID002","Hello Word 1".getBytes()));
        messages.add(new Message(topic,"TagA","OrderID003","Hello Word 3".getBytes()));
        // 保证消息不超过4M 通过 ListSplitter 将消息列表分割为较小的片段，并使用生产者（producer）对象将每个片段发送出去
        ListSplitter splitter = new ListSplitter(messages);
        while (splitter.hasNext()) {
            try {
                List<Message>  listItem = splitter.next();
                SendResult send = batchProducer.send(listItem);
                System.out.printf("%s%n", send);
            } catch (Exception e) {
                e.printStackTrace();
                //handle the error
            }
        }
        batchProducer.shutdown();

    }
}
