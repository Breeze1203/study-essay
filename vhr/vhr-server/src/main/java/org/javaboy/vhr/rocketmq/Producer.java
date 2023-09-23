package org.javaboy.vhr.rocketmq;

import com.alibaba.fastjson.JSON;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.javaboy.vhr.bean.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

/**
 * @projectName: vhr
 * @package: org.javaboy.vhr.rocketmq
 * @className: Producer
 * @author: pt3548297839
 * @description: TODO
 * @date: 2023/9/23 10:38
 * @version: 1.0
 */
@Component
public class Producer {
    @Autowired
    DefaultMQProducer defaultMQProducer;
    public void sendMessage(Employee employee) {
        try {
            defaultMQProducer.start();
        } catch (MQClientException e) {
            throw new RuntimeException(e);
        }
        try {
            byte[] bytes = JSON.toJSONString(employee).getBytes("utf-8");
            Message message = new Message("OnboardingMail", bytes);
            defaultMQProducer.send(message);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } catch (MQBrokerException e) {
            throw new RuntimeException(e);
        } catch (RemotingException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (MQClientException e) {
            throw new RuntimeException(e);
        }
    }
}
