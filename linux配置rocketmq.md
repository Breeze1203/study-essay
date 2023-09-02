下载jdk,maven,rocketmq并配置环境

```
vim /etc/profile
```



![](linux图片\jdk.png)

下载rocketm二进制包

```
wget https://dist.apache.org/repos/dist/release/rocketmq/5.1.3/rocketmq-all-5.1.3-bin-release.zip
```

解压并配置环境

```
unzip rocketmq-all-5.1.3-source-release.zip
```

关于rocketmq的一些启动参数数值过大进行修改

```
cd bin
vim runserver.sh
vim runbroker.sh
退出保存
```

![](linux图片\参数.png)

![](linux图片\参数1.png)

关于我是在linux虚拟机上安装,所以的配置主机与虚拟机直接的网络

*先放行rocketmq所用的端口号9876 10911*

使用root用户

```
sudo firewall-cmd --zone=public --add-port=10911/tcp --permanent
sudo firewall-cmd --reload
sudo firewall-cmd --zone=public --add-port=9876/tcp --permanent
sudo firewall-cmd --reload
```

启动namesrv和broker

```
sh bin/mqnamesrv &
sh bin/mqbroker -n localhost:9876
```

启动成功

![](linux图片\success.png)

在windows主机上使用telnet命令进行测试

telnet 《linux ip》地址  port《端口号》

成功即可使用代码连接

```java
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
        DefaultMQProducer producer=new DefaultMQProducer("please_rename_unique_group_name");
        producer.setNamesrvAddr("192.168.133.132:9876");
        try {
            producer.start();
            System.out.println(producer.getBackPressureForAsyncSendNum());
            producer.setRetryTimesWhenSendFailed(10000);
            int messageCount=10;
            final CountDownLatch countDownLatch=new CountDownLatch(messageCount);
            for (int i = 0; i < messageCount; i++) {
                final int index=1;
                Message message=new Message("TopTest","TagA","OrderID188",("hello rocketmq"+i).getBytes(RemotingHelper.DEFAULT_CHARSET));
                producer.send(message,new SendCallback(){

                    @Override
                    public void onSuccess(SendResult sendResult) {
                        countDownLatch.countDown();
                        System.out.printf("%-10d OK %n %s",index,sendResult.getMsgId());
                    }

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

```

![](linux图片\success2.png)