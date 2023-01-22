## Linux

命令行界面里面下达指令时，会有两种主要的情况：
一种是该指令会直接显示结果然后回到命令提示字符等待下一个指令的输入；
一种是进入到该指令的环境，直到结束该指令才回到命令提示字符的环境，用quit 退出

[Tab] 接在一串指令的第一个字的后面，则为“命令补全”；
[Tab] 接在一串指令的第二个字以后时，则为“文件补齐”！
若安装 bash-completion 软件，则在某些指令后面使用 [tab] 按键时，可以进行“选项/参

```properties
[dmtsai@study ~]$ ca[tab][tab] &lt;==[tab]按键是紧接在 a 字母后面！
cacertdir_rehash cairo-sphinx cancel case
cache_check cal cancel.cups cat
cache_dump calibrate_ppa capsh catchsegv
cache_metadata_size caller captoinfo catman
# 上面的 [tab] 指的是“按下那个tab键”，不是要你输入中括号内的tab啦！


[dmtsai@study ~]$ date --[tab][tab] &lt;==[tab]按键是紧接在 -- 后面！
--date --help --reference= --rfc-3339= --universal
--date= --iso-8601 --rfc-2822 --set= --version
# 瞧！系统会列出来 date 这个指令可以使用的选项有哪些喔～包括未来会用到的 --date 等项目

```

ctrl+c 中断程序     ctrl+d  键盘输入结束(有时可取代exit)   [shift]+{[PageUP]|[Page Down]  向前翻页/向后 

man+指令(求助)   离开man page  指令q  /string 向“下”搜寻 string 这个字串，如果要搜寻 vbird 的话，就输入 /vbird    ?string 向“上”搜寻 string 这个

字串使用 -f 这个选项就可以取得更多与man相关的信息

```properties
[dmtsai@study ~]$ man -f man
man （1） - an interface to the on-line reference manuals
man （1p） - display system documentation
man （7） - macros to format man pages
```

##### 超简单文书编辑器nano

```properties
[dmtsai@study ~]$ nano text.txt
# 不管text.txt存不存在都没有关系！存在就打开旧文件，不存在就打开新文件
GNU nano 2.3.1 File: text.txt
&lt;==这个是光标所在处
[ New File ]
^G Get Help ^O WriteOut ^R Read File ^Y Prev Page ^K Cut Text ^C Cur Pos
^X Exit ^J Justify ^W Where Is ^V Next Page ^U UnCut Te ^T To Spell
# 上面两行是指令说明列，其中^代表的是[ctrl]的意思
[ctrl]-G：取得线上说明（help），很有用的！

[ctrl]-X：离开naon软件，若有修改过文件会提示是否需要储存喔！

[ctrl]-O：储存盘案，若你有权限的话就能够储存盘案了；

[ctrl]-R：从其他文件读入数据，可以将某个文件的内容贴在本文件中；

[ctrl]-W：搜寻字串，这个也是很有帮助的指令喔！

[ctrl]-C：说明目前光标所在处的行数与列数等信息；

[ctrl]-_：可以直接输入行号，让光标快速移动到该行；

[alt]-Y：校正语法功能打开或关闭（按一下开、再按一下关）

[alt]-M：可以支持鼠标来移动光标的功能

```

##### 开关机

将数据同步写入硬盘中的指令： sync  在内存中尚未被更新的数据，就会被写入硬盘中
惯用的关机指令： shutdown
重新开机，关机： reboot, halt, poweroff

```properties
[root@study ~]# shutdown -h now
立刻关机，其中 now 相当于时间为 0 的状态
[root@study ~]# shutdown -h 20:25
系统在今天的 20:25 分会关机，若在21:25才下达此指令，则隔天才关机
[root@study ~]# shutdown -h +10
系统再过十分钟后自动关机
[root@study ~]# shutdown -r now
系统立刻重新开机
[root@study ~]# shutdown -r +30 'The system will reboot'
再过三十分钟系统会重新开机，并显示后面的讯息给所有在线上的使用者
[root@study ~]# shutdown -k now 'This system will reboot'
仅发出警告信件的参数！系统并不会关机啦！吓唬人！
[root@study ~]# systemctl [指令]
指令项目包括如下：
halt 进入系统停止的模式，屏幕可能会保留一些讯息，这与你的电源管理模式有关
poweroff 进入系统关机模式，直接关机没有提供电力喔！
reboot 直接重新开机
suspend 进入休眠模式
[root@study ~]# systemctl reboot # 系统重新开机
[root@study ~]# systemctl poweroff # 系统关机
```

##### linux的文件权限与目录配置

Linux一般将文件可存取的身份分为三个类别，分别是 owner/group/others，且三种身份各有 read/write/execute 等权限

oot的相关信息， 都是记录在/etc/passwd这个文件内的。至于个人的密码则是记录
在/etc/shadow这个文件下。 此外，Linux所有的群组名称都纪录在/etc/group内

su 切换root身份  离开 su - 则使用 exit 回到 dmtsai 的身份即可

ls -al则表示列出所有的文件详细的权限与属性 （包含隐藏文件，就是文件名第一个字符为“ . ”的文件）

![](D:\everything\linux截图\1.png)

------

![](D:\everything\linux截图\2.png)



x=1  w=2  r=4

当为[ d ]则是目录，例如上表文件名为“.config”的那一行；
当为[ - ]则是文件，例如上表文件名为“initial-setup-ks.cfg”那一行；
若是[ l ]则表示为链接文件（link file）；
若是[ b ]则表示为设备文件里面的可供储存的周边设备（可随机存取设备）；
若是[ c ]则表示为设备文件里面的序列埠设备，例如键盘、鼠标（一次性读取设
备）。
接下来的字符中，以三个为一组，且均为“rwx” 的三个参数的组合。其中，[ r ]代表可读
（read）、[ w ]代表可写（write）、[ x ]代表可执行（execute）。 要注意的是，这三个
权限的位置不会改变，如果没有权限，就会出现减号[ - ]而已。

chgrp ：改变文件所属群组
chown ：改变文件拥有者
chmod ：改变文件的权限, SUID, SGID, SBIT等等的特性

```properties
[root@study ~]# chgrp [-R] dirname/filename ...
选项与参数：
-R : 进行递回（recursive）的持续变更，亦即连同次目录下的所有文件、目录
都更新成为这个群组之意。常常用在变更某一目录内所有的文件之情况。
范例：
[root@study ~]# chgrp users initial-setup-ks.cfg
[root@study ~]# ls -l
-rw-r--r--. 1 root users 1864 May 4 18:01 initial-setup-ks.cfg
[root@study ~]# chgrp testing initial-setup-ks.cfg
chgrp: invalid group: `testing' &lt;== 发生错误讯息啰～找不到这个群组名
[root@study ~]# chown [-R] 帐号名称 文件或目录
[root@study ~]# chown [-R] 帐号名称:群组名称 文件或目录
选项与参数：
-R : 进行递回（recursive）的持续变更，亦即连同次目录下的所有文件都变更
范例：将 initial-setup-ks.cfg 的拥有者改为bin这个帐号：
[root@study ~]# chown bin initial-setup-ks.cfg
[root@study ~]# ls -l
-rw-r--r--. 1 bin users 1864 May 4 18:01 initial-setup-ks.cfg
范例：将 initial-setup-ks.cfg 的拥有者与群组改回为root：
[root@study ~]# chown root:root initial-setup-ks.cfg
[root@study ~]# ls -l
-rw-r--r--. 1 root root 1864 May 4 18:01 initial-setup-ks.cfg
```

将.bashrc这个文件拷贝成为.bashrc_test文件名   复制行为（cp）会复制执行者的属性与权限

```properties
[root@study ~]# cp 来源文件 目的文件
```

##### 改变文件权限

假如我们要“设置”一个文件的权限成为“-rwxr-xr-x”时

```properties
chmod u=rwx,g=rx,o=rx .bash_history
```

如果要删除已有的某个权限,比如上面权限变为-rwx------

```properties
chmod g-rx,o-rx .bash_histor
```

如果在原来的基础上增加某个权限

```properties
chmod g+r,o+w .bash_history
```

拿掉全部人的可执行权限

```properties
[root@study ~]# chmod a-x .bashrc
mkdir testing &lt;==创建新目录
touch testing/testing &lt;==创建空的文件
```

文件名称的开头为小数点“.”时， 代表这个文件为“隐藏文件”

#####  磁盘管理

1.切换目录:cd  目录名

2.展示当前所在目录:pwd

3.回到根目录:cd ..或cd /

Tab键能自动不全，若不能不全，则给提示

4.列出文件或目录名 ls ll(详细信息)

5.创建目录: mkdir 目录名   创建多个  mkdir test01 test02

6.删除文件：rm  my.txt(文件名)  rm -f my.txt(不用选择确认删除)

7.将字符串输出到文件里面：echo "1213">abc.txt

8.查看文件里写了什么： cat abc.txt  也可以查看文件的信息(绝对路径的形式)

9.删除文件夹:rm -rf text01  r(表示递归删除)

10.复制文件:cp   复制的文件名    新文件名

11.复制文件夹:cp  -rf   复制的文件夹名   新文件夹名

12.编辑文件:vi  文件名(按 i 进入编辑模式  按Esc退出编辑模式  Ctrl+shift +i ：wq(保存退出) q(不保存退出))

13.more  文件名(查看文件内容) 按回车 一行一行查看，按空格逐屏展示

14.查看文件开头的n行数据:head a.txt(默认十行)  head a.txt -n 10(显示开头10行)

```properties
[root@Breeze a]# head a.txt -n 2
java
JAVA
```

15.显示尾部指定的n行数据: tail  a.txt -n 10

```properties
root@Breeze a]# tail a.txt -n 2
javacscs

```

16.在一个文件中搜索:grep java a.txt(默认区分大小写) grep -i java a.txt(不区分大小写) 搜索有空格要带引号

```properties
[root@Breeze a]# grep java a.txt
java
javacscs
[root@Breeze a]# grep -i java a.txt
java
JAVA
JAVA SCSDF
javacscs
```

17.搜索单词:grep -w java a.txt

```properties
[root@Breeze a]# grep -w java a.txt
java
[root@Breeze a]# grep -wi java a.txt
java
JAVA
JAVA SCSDF

```

18.多个文件中搜索:grep java a.txt b.txt

```properties
[root@Breeze a]# grep java a.txt b.txt
a.txt:java
a.txt:javacscs
b.txt:java
```

19.使用管道:   | 例如  cat a.txt | grep java 相当于 grep java a.txt

```properties
[root@Breeze a]# cat a.txt b.txt | grep java 
java
javacscs
java
```

20.显示系统当前时间:date

```properties
[root@Breeze a]# date
Wed Dec 28 19:48:28 CST 2022
```

21.切换用户:su 用户名

22.查看进程:ps 参数(例如 ps -ef)

```properties
[root@Breeze a]# ps -ef
UID         PID   PPID  C STIME TTY          TIME CMD
root          1      0  0 18:01 ?        00:00:03 /usr/lib/systemd/systemd --switched-root --system --deserialize 22
root          2      0  0 18:01 ?        00:00:00 [kthreadd]
root          4      2  0 18:01 ?        00:00:00 [kworker/0:0H]
root          6      2  0 18:01 ?        00:00:00 [ksoftirqd/0]
root          7      2  0 18:01 ?        00:00:00 [migration/0]
root          8      2  0 18:01 ?        00:00:00 [rcu_bh]
root          9      2  0 18:01 ?        00:00:07 [rcu_sched]
root         10      2  0 18:01 ?        00:00:00 [lru-add-drain]
root         11      2  0 18:01 ?        00:00:00 [watchdog/0]
root         12      2  0 18:01 ?        00:00:00 [watchdog/1]
root         13      2  0 18:01 ?        00:00:00 [migration/1]
root         14      2  0 18:01 ?        00:00:00 [ksoftirqd/1]
root         16      2  0 18:01 ?        00:00:00 [kworker/1:0H]
root         18      2  0 18:01 ?        00:00:00 [kdevtmpfs]
root         19      2  0 18:01 ?        00:00:00 [netns]

```

23.杀死进程:kill  进程id

24.压缩/解压:tar  参数  文件/目录

```properties
[root@Breeze home]# tar -cf d.tar.gz c
[root@Breeze home]# ls  // 压缩
a  c  c.tar.gz  d.tar.gz  pt
```

```properties
[root@Breeze home]# tar -xf d.tar.gz c
[root@Breeze home]# ls   // 解压
a  c  c.tar.gz  d.tar.gz  pt
```

独立命令：压缩解压都要用到其中一个，可以和别的命令连用，但只能用其中一个。

-c	创建新的压缩文件
-x	解压缩文件
-t	
列出文件内容
-r	新增文件到压缩归档文件末尾
-u	更新原压缩包中的文件
可选命令：可以与独立命令连用。

-z	有gzip属性的
-j	有bz2属性的
-Z	有compress属性的
-v	
显示指令执行过程
-O	将文件解开到标准输出
-w
遭遇问题时先询问用户
- k
  解开备份文件时，不覆盖已有的文件

- m
  还原文件时，不变更文件的更改时间
  C<目的目录>	
  切换到指定的目录

- f< 备份文件 >
  指定压缩文件（最后一个参数）

- ```properties
  # tar -cf all.tar *.jpg 
  将所有.jpg的文件压缩成all.tar的包。-c是表示创建新压缩包，-f指定包的文件名。
  # tar -rf all.tar *.gif 
  将所有.gif的文件增加到all.tar的包里面去。-r是表示增加文件的意思。
  # tar -uf all.tar logo.gif 
  更新原来tar包all.tar中logo.gif文件，-u是表示更新文件的意思。
  # tar -tf all.tar 
  列出all.tar包中所有文件，-t是列出文件的意思
  # tar -xf all.tar 
  解压缩all.tar包中所有文件，-x是解压缩的意思
  //将目录里所有jpg文件打包成tar.jpg
  tar –cvf jpg.tar *.jpg 
   
  //将目录里所有jpg文件打包成jpg.tar后，用gzip压缩，生成zip压缩包，命名jpg.tar.gz
  tar –czf jpg.tar.gz *.jpg   
   
  //将目录里所有jpg文件打包成jpg.tar后，用bzip2压缩，生成bzip2压缩包，命名为jpg.tar.bz2
  tar –cjf jpg.tar.bz2 *.jpg 
   
  //将目录里所有jpg文件打包成jpg.tar后，用compress压缩，生成compress压缩包，命名为jpg.tar.Z
  tar –cZf jpg.tar.Z *.jpg  
   
  //rar格式的压缩，需要先下载rar for linux
  rar jpg.rar *.jpg 
   
  //zip格式的压缩，需要先下载zip for linux
  zip jpg.zip *.jpg 
  ```

  25.查看压缩文件:tar -tf  压缩文件名

  #####   网络通讯

  1.查看IP:ifconfig

  2.停止：ctrl+c

  3.下载资源:wget 下载资源的路径

  ##### 安装软件

  1.查找软件包:yum search  安装包名称

  2.安装指定软件:yum install 安装包

  3.删除安装包:yum remove  安装包

  4.删除已安装软件的安装包:yum clean all

  5.启动程序:./ +  程序

  ##### 安装软件快捷方式

  1.cd  /usr/share/applications

  2.通过su命令进入root模式

  3.输入命令vim idea.desktop

  4.输入 保存

  ```properties
  [Desktop Entry]

  Name=idea

  Exec=/home/idea-IU-163.12024.16/bin/idea.sh %u // idea.sh所在路径

  Type=Application

  Icon=/home/idea-IU-163.12024.16/bin/idea.png  // idea图标所在路径

  Terminal=false

  ```

  5.在/usr/share/applications路径下就有图标，复制到桌面就行

  ​

  ​





