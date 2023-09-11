package org.example.message;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;

/*
发送同步消息
 */

public class SyncProducer {
    public static void main(String[] args) throws UnsupportedEncodingException, MQBrokerException, RemotingException, InterruptedException {
        // 创建生产者实列
        DefaultMQProducer producer=new DefaultMQProducer("SyncProducer_Test");
        producer.setNamesrvAddr("192.168.133.143:9876");
        try {
            producer.start();
            for (int i = 0; i < 10; i++) {
                Message message=new Message("Sync_Test","TagA",("发送的是同步消息"+i).getBytes(RemotingHelper.DEFAULT_CHARSET));
                message.setKeys("同步消息");
                SendResult send = producer.send(message);
                System.out.printf("%s%n",send);
            }
//            producer.shutdown();
        } catch (MQClientException e) {
            throw new RuntimeException(e);
        }
    }
}
