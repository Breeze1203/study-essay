package org.example.message;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.CountDownLatch;

// 同步发送
public class AsyncProducer {
    public static void main(String[] args) {
        DefaultMQProducer producer=new DefaultMQProducer("Async_group");
        producer.setNamesrvAddr("192.168.133.143:9876");
        try {
            producer.start();
            System.out.println(producer.getBackPressureForAsyncSendNum());
            // 消息发送失败不重试
            producer.setRetryTimesWhenSendFailed(0);
            int messageCount=10;
            // 十条消息创建10个线程池
            final CountDownLatch countDownLatch=new CountDownLatch(messageCount);
            for (int i = 0; i < messageCount; i++) {
                final int index=1;
                Message message=new Message("Async_Test","TagA","Async",("异步发送消息"+i).getBytes(RemotingHelper.DEFAULT_CHARSET));
                // 异步发送消息  SendCallback是一个回调接口 定义了两个方法
                producer.send(message,new SendCallback(){
                    // 发送成功
                    @Override
                    public void onSuccess(SendResult sendResult) {
                        countDownLatch.countDown();
                        System.out.printf("异步发送成功 %s %n",index,sendResult.getMsgId());
                    }
                    // 发送失败
                    @Override
                    public void onException(Throwable throwable) {
                        countDownLatch.countDown();
                        System.out.printf("%-10d OK %n %s",index,throwable);
                    }
                });
            }
        } catch (MQClientException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } catch (RemotingException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
