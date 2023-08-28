编译安装rocketMQ

1.安装jdk并进行环境配置

2.安装maven，修改镜像，配置maven环境变量

3.安装rocketMQ源码包编译成二进制文件

$ unzip rocketmq-all-5.1.3-source-release.zip
$ cd rocketmq-all-5.1.3-source-release/
$ mvn -Prelease-all -DskipTests -Dspotbugs.skip=true clean install -U
$ cd distribution/target/rocketmq-5.1.3/rocketmq-5.1.3

### 启动namesrv
$ nohup sh bin/mqnamesrv &

### 验证namesrv是否启动成功
$ tail -f ~/logs/rocketmqlogs/namesrv.log
The Name Server boot success...

### 先启动broker
$ nohup sh bin/mqbroker -n localhost:9876 --enable-proxy &

### 验证broker是否启动成功, 比如, broker的ip是192.168.1.2 然后名字是broker-a
$ tail -f ~/logs/rocketmqlogs/proxy.log 
The broker[broker-a,192.169.1.2:10911] boot success...

