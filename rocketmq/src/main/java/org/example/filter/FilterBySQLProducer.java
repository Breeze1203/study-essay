package org.example.filter;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * @projectName: rocketmq
 * @package: org.example.filter
 * @className: FilterBySQLProducer
 * @author: pt3548297839
 * @description: Sql表达式过滤
 * @date: 2023/9/10 20:11
 * @version: 1.0
 */
public class FilterBySQLProducer {
    public static void main(String[] args) throws MQClientException {
        DefaultMQProducer producer = new DefaultMQProducer("FilterBySql_Group");
        producer.setNamesrvAddr("192.168.3.54:9876");
        producer.start();
        String[] tags = {"TagA","TagB","TagC","TagD"};
        for (int i = 0; i < 10; i++) {
            try {
                String tag = tags[i % tags.length];
                //构建消息
                Message msg = new Message("TopicTest" /* Topic */,
                        tag /* Tag */,
                        ("RocketMQ消息测试，消息的TAG="+tag+  ", 属性 age = " + i + ", == " + i).getBytes(RemotingHelper.DEFAULT_CHARSET)
                );
                //每个消息设置属性值 age ，值 0-9
                msg.putUserProperty("age", i+"");
                SendResult sendResult = producer.send(msg);
                System.out.printf("%s%n", sendResult);
            } catch (Exception e) {
                e.printStackTrace();
//                Thread.sleep(1000);
            }
        }
        //Shut down once the producer instance is not longer in use.
        producer.shutdown();
    }
}
