# Redis

##### 简介：

redis是一个开源的，遵循BSD协议的，是一个高性能的key-value数据库。

##### 用处和意义：

$\textcolor{red}{Redis的数据存放在内存(内存形数据库)}​$，所以速度快但是会受到内存空间限制。MySQL存放在硬盘，在速度上没有Redis快，但是存放的数据量要多的多。所以redis适合放一些频繁使用，比较热的数据，因为是放在内存中，读写速度都非常快，一般会应用在下面一些场景:排行榜、计数器、消息队列推送。目前大多数公司的存储都是mysql + redis，mysql作为主存储，redis作为辅助存储被用作缓存，加快访问读取的速度，提高性能

##### 优势：

性能极高

支持丰富的数据类型：String(字符串)、Hash(字典）、List(列表)、Set(集合)、Sorted Set(有序集合)。

丰富的特性：支持publish/subscribe，通知，key过期等等特征

原子性：要么成功执行，要么失败，通过MULTI和EXEC指令包起来

##### linux安装redis：

redis安装包地址:https://download.redis.io/releases/redis-6.0.8.tar.gz

##### 五种数据类型:

###### String:

String是Redis里边最简单的一种数据结构。在redis中，所有的key都是字符串，但是不同的key对应的value则具备不同的数据结构，我们所说的五种不同的数据类型，主要是指value的数据类型不同。

Redis中的字符串是动态字符串，内部是可以修改的，像java中的StringBuffer,它采用分配冗余空间的方式减少内存的频繁分配，在redis的内部结构中，一般实际分配的内存会大于需要的内存，当字符串小于1M的时候，扩容都是在现有的空间基础上加倍，扩容每次扩1M空间，最大为512M

- set

是给一个key赋值 

```properties
127.0.0.1:6379> set k5 99
OK
127.0.0.1:6379> get k5
"99"
```

- append

使用append命令时，如果key已经存在，则直接在value后追加值，否则会创建新的键值对

```properties
127.0.0.1:6379> APPEND k1 javaboy
(integer) 7
127.0.0.1:6379> get k1
"javaboy"
127.0.0.1:6379> APPEND k1 .org
(integer) 11
127.0.0.1:6379> get k1
"javaboy.org"
```

- decr

可以实现对value的减一操作(前提收value是一个数字)，如果value不是数字会报错，如果value不粗俺咋，则会给一个默认的值0,在默认值的基础上减一

- decrby

跟decrby类似，但可以自己设置步长

```properties
127.0.0.1:6379> get k5
"99"
127.0.0.1:6379> decrby k5 66
(integer) 33
127.0.0.1:6379> 
```

- get

get用来获取一个Key的value

- getranget

可以用来返回Key对应的value的字串，这点有点类似与substring

```properties
127.0.0.1:6379> get k1
"javaboy.org"
127.0.0.1:6379> getrange k1 2 6
"vaboy"
```

- getset

获取并更新

```properties
127.0.0.1:6379> getset k5  22
"33"
127.0.0.1:6379> get k5
"22"
```

- incr 

给某一个key的value自增

- incrby

给某一个key的value自增，同时还可以设置步长

```properties
127.0.0.1:6379> get k5
"22"
127.0.0.1:6379> incr k5
(integer) 23
127.0.0.1:6379> incrby k5 77
(integer) 100
```

- incrbyfloat

和incrby类似，但是自增的步长可以设置为浮点数

```properties
127.0.0.1:6379> incrby k5 77
(integer) 100
127.0.0.1:6379> INCRBYFLOAT k5 0.3
"100.3"
```

- mget和mset

批量获取和批量存储

```properties
127.0.0.1:6379> mget k1 k2 k5
1) "javaboy.org"
2) "-2"
3) "100.3"
127.0.0.1:6379> mset k1 org.javaboy k2 8 k3 100.7
OK
127.0.0.1:6379> mget k1 k2 k5
1) "org.javaboy"
2) "8"
3) "100.3"
```

- setex

给key设置value的同时，还设置过期时间

```properties
127.0.0.1:6379> setex k7 10 python
OK
```

- psetex

和setex类似，只不过时间单位是毫秒

- setnx

默认情况下，set命令会覆盖已经存在的key，setnx则不会

- msetnx

批量设置

- setrange

覆盖一个已经存在的key的value

```properties
127.0.0.1:6379> get k1
"org.javaboy"
127.0.0.1:6379> setrange k1 0 com
(integer) 11
127.0.0.1:6379> get k1
"com.javaboy"
```









