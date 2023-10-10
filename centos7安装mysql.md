centos7安装mysql

wget https://downloads.mysql.com/archives/get/p/23/file/mysql-8.0.20-1.el7.x86_64.rpm-bundle.tar

tar -xf mysql-8.0.20-1.el7.x86_64.rpm-bundle.tar

rpm -ivh mysql-community-common-8.0.20-1.el7.x86_64.rpm 

rpm -ivh mysql-community-libs-8.0.20-1.el7.x86_64.rpm 

rpm -ivh mysql-community-client-8.0.20-1.el7.x86_64.rpm 

rpm -ivh mysql-community-server-8.0.20-1.el7.x86_64.rpm

rpm -ivh mysql-community-test-8.0.20-1.el7.x86_64.rpm

检查是否安装完成

mysql --version  已下显示则表明安装完成

**[root@localhost mysql]# mysqladmin --version**
mysqladmin  Ver 8.0.20 for Linux on x86_64 (MySQL Community Server - GPL)
**[root@localhost mysql]# mysql --version**
mysql  Ver 8.0.20 for Linux on x86_64 (MySQL Community Server - GPL)

***[root@localhost mysql]# rpm -qa | grep -i mysql***
mysql-community-client-8.0.20-1.el7.x86_64
mysql-community-common-8.0.20-1.el7.x86_64
mysql-community-server-8.0.20-1.el7.x86_64
MySQL-shared-compat-5.6.40-1.el7.x86_64
mysql-community-libs-8.0.20-1.el7.x86_64
mysql-community-test-8.0.20-1.el7.x86_64

**[root@localhost mysql]# rpm -qa | grep -i mysql**
mysql-community-client-8.0.20-1.el7.x86_64
mysql-community-common-8.0.20-1.el7.x86_64
mysql-community-server-8.0.20-1.el7.x86_64
MySQL-shared-compat-5.6.40-1.el7.x86_64
mysql-community-libs-8.0.20-1.el7.x86_64
mysql-community-test-8.0.20-1.el7.x86_64

服务初始化

为了保证数据库目录和文件所有者为mysql登录用户，root身份下执行mysql服务

mysqld --initialize --user=mysql

查看密码

cat /var/log/mysqld.log

查看mysql运行状态

systemctl status mysqld

开启mysql服务

systemctl start mysqld

检查开机是否自启动

[root@localhost mysql]# systemctl list-unit-files | grep mysqld.service
mysqld.service                                enabled

enabled就是自启动

如果不是执行下面命令

systemctl  enable  mysqld.service

关闭开机自启动

systemctl  disabel  mysqld.service

首次登录

mysql -uroot -p 

输入之前查看的密码

修改密码：alter user 'root'@'localhost' identified by '密码';

quit退出，再次登录（新密码）

远程连接

开启3306端口

firewall-cmd --zone=public --add-port=3306/tcp --permanent

刷新 firewall-cmd --reload

默认情况下，服务器上的 MySQL 是不允许 root 用户远程连接的。我们要修改这个默认设置。

进入服务器的 MySQL

使用mysql数据库  use mysql;

查询数据表 use的host 和user字段信息  select host, user from user;

mysql> use mysql
Reading table information for completion of table and column names
You can turn off this feature to get a quicker startup with -A

Database changed
mysql> select user,host from user;
+------------------+-----------+
| user             | host      |
+------------------+-----------+
| root             | %         |
| mysql.infoschema | localhost |
| mysql.session    | localhost |
| mysql.sys        | localhost |
+------------------+-----------+
4 rows in set (0.00 sec)

%是运行所有ip访问，你可以通过

update  user set host='允许远程连接的IP地址' where user=root;

最后刷新权限  不刷新不起效

flush privileges;

开启防火墙3306端口

firewall-cmd --zone=public --add-port=3306/tcp --permanent

firewall-cmd --reload 刷新





