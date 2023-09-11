package org.example.order;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.util.List;


/*
顺序消息发送
1.生产者：单线程发送，保证发送端的顺序性
2.broker：保证顺序消息 路由到同意队列
3.消费者：单线程发送，不能使用并发消费
 */
public class OrderedProducer {
    public static void main(String[] args) throws Exception {
        //Instantiate with a producer group name.
        DefaultMQProducer producer = new DefaultMQProducer("Order_Group");
        // Specify name server addresses.
        producer.setNamesrvAddr("192.168.133.143:9876");
        //Launch the instance.

        //二组订单为例，每组订单发送5条
        producer.start();
        for (int i = 0; i < 5; i++) { //每个订单的消息数量 0-4
            for (int j = 100; j < 102; j++) { //订单号 100 、1001
                int orderId = j; //订单Id 100 是一个队列 、101 是一个队列，每个订单发送5条消息
                //构建message对象
                Message msg = new Message("Order_Test", ("orderId:" + orderId + ",num:" + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
                //生产者:单线程发送
                //指定topic ，选择topic对应的队列
                //MessageQueueSelector 实现Queue选择，达到相同的订单id获取相同的队列
                SendResult sendResult = producer.send(msg,
                        new MessageQueueSelector() {
                            @Override
                            //mqs:当前topic包含多少个队列，返回值 当前的消息对应的消息队列
                            public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
                                Integer id = (Integer) arg; //订单ID
                                int index = id % mqs.size(); //100%3 ,101%3
                                //相同的订单ID，会得到相同的队列
                                MessageQueue queue = mqs.get(index);
                                System.out.println("orderId:" + id + ",index:" + index + ",queue:" + queue);
                                return queue;
                            }
                        }, orderId);

//                System.out.printf("%s%n", sendResult);
            }

        }
        //server shutdown
        producer.shutdown();
    }
}
