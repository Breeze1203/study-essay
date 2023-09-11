package org.example.filter;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;

/**
 * @projectName: rocketmq
 * @package: org.example.filter
 * @className: FilterByTagProducer
 * @author: pt3548297839
 * @description: Sql表达式过滤
 * @date: 2023/9/10 20:10
 * @version: 1.0
 * 通过消息的属性运行sql过滤表达式进行条件匹配，消息发送
 * 时需要设置用户的属性putUserProperty方法设置属性
 * 支持的语法：
 * 1.数值比较，如 >,>=,<,<=,BETWEEN,=;
 * 2.字符比较，如=，<>,IN
 * 3.is null or is not null
 * 4.逻辑连接符and or not
 * 支持的类型
 * 1.数值型，如123，3.1415
 * 2.字符型，如'abc',必须使用单引号
 * 3.null,特殊常数
 * 4，布尔值，True or False
 */
public class FilterByTagProducer {
    public static void main(String[] args) throws UnsupportedEncodingException, MQBrokerException, RemotingException, InterruptedException, MQClientException {
        DefaultMQProducer producer = new DefaultMQProducer("FilterTyTag_Group");
        producer.setNamesrvAddr("192.168.3.54:9876");
        producer.start();
        String[] tags = {"TAG-A", "TAG-B", "TAG-C"};
        for (int i = 0; i < 10; i++) {
            String tag = tags[i % tags.length];
            //每个消息设置一个tag，tag 二级分类
            Message msg = new Message("FilterTag_Test", tag,
                    ("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET)
            );
            SendResult sendResult = producer.send(msg);
            System.out.printf("%s%n", sendResult);
        }
    }
}
