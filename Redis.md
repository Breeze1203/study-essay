

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

wget  https://download.redis.io/releases/redis-6.0.8.tar.gz

tar -zvxf  '解压'

编译

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

##### bit

在redis中，字符串都是以二进制形式存储的，bit相关的命令都是对二进制操作的

- getbit  key对应的value在offset处对应的bit值
- setbit  修改key对应value在offset处对应的bit值
- bitcount  统计二进制数据中1的个数

##### List

- Ipush 将value的值从左到右依次插入表头位置

- lrange 返回列表值定区间内的元素

  ```properties
  127.0.0.1:6379> lpush key1 java.boy.org java.gitub.org www.baidu.com
  (integer) 3
  127.0.0.1:6379> LRANGE KEY1 0 1
  (empty list or set)
  127.0.0.1:6379> lrange key1 0 1
  1) "www.baidu.com"
  2) "java.gitub.org"
  ```

- rpush 将value的值从右往左依次插入表头位置

  ```properties
  127.0.0.1:6379> RPUSH key2 java python go c++
  (integer) 4
  127.0.0.1:6379> LRANGE key2 0 3
  1) "java"
  2) "python"
  3) "go"
  4) "c++"

  ```

- rpop 移除并返回尾元素

  ```properties
  127.0.0.1:6379> RPUSH key2 java python go c++
  (integer) 4
  127.0.0.1:6379> RPOP key2 
  "c++"
  ```

- lpop 移除并返回头元素

```properties
127.0.0.1:6379> LPOP key2
"java"
```

- lindex 返回列表中指定下标元素

  ```properties
  127.0.0.1:6379> LINDEX key2 1
  "go"
  ```

- ltrim 对一个列表进行修剪

```properties
127.0.0.1:6379> RPUSH key3 c# java python go c++
(integer) 5
127.0.0.1:6379> LTRIM key3 1 -1
OK
127.0.0.1:6379> LINDEX key3 1
"python"
```

- blpop 阻塞式弹出，相当于lpop的阻塞版

  ```properties
  127.0.0.1:6379> BLPOP key3 10
  1) "key3"
  2) "java"
  127.0.0.1:6379> BLPOP key3 10
  1) "key3"
  2) "python"
  127.0.0.1:6379> BLPOP key3 10
  1) "key3"
  2) "go"
  127.0.0.1:6379> BLPOP key3 10
  1) "key3"
  2) "c++"
  127.0.0.1:6379> BLPOP key3 10
  (nil)
  (10.06s)
  ```

  ##### set

  - sadd  添加元素到一个key中

  ```properties
  127.0.0.1:6379> SADD key4 w o p o p p 
  (integer) 3
  ```

  - smembers 获取一个key下的所有元素

  ```properties
  127.0.0.1:6379> SMEMBERS key4
  1) "o"
  2) "w"
  3) "p"
  ```

  - srem 移除指定的元素

  ```properties
  127.0.0.1:6379> SMEMBERS key4
  1) "o"
  2) "w"
  3) "p"
  127.0.0.1:6379> SREM key4 p
  (integer) 1
  127.0.0.1:6379> SMEMBERS key4
  1) "o"
  2) "w"
  ```

  - sismemeber 返回某一个成员是否在集合中

  ```properties
  127.0.0.1:6379> SMEMBERS key4
  1) "o"
  2) "v"
  3) "w"
  4) "e"
  5) "b"
  127.0.0.1:6379> SISMEMBER key4 w
  (integer) 1
  127.0.0.1:6379> SISMEMBER key4 l
  (integer) 0
  ```

  - scard 返回集合的数量

  ```properties
  127.0.0.1:6379> SCARD key4
  (integer) 5
  ```

  - srandmember 随机返回一个元素

  ```properties
  127.0.0.1:6379> SRANDMEMBER key4
  "e"
  127.0.0.1:6379> SRANDMEMBER key4
  "v"
  ```

  - spop 随机返回并且出栈一个元素

  ```properties
  127.0.0.1:6379> SPOP key4 2
  1) "w"
  2) "e"
  127.0.0.1:6379> SMEMBERS key4
  1) "o"
  2) "v"
  3) "b"
  ```

  - smove把一个元素从一个集合移到另一个集合中

  ```properties
  127.0.0.1:6379> sadd key2 a d f g g
  (integer) 4
  127.0.0.1:6379> SMOVE key2 key1 a
  (integer) 1
  127.0.0.1:6379> SMEMBERS key1
  1) "f"
  2) "a"
  ```

  - sdiff 返回两个集合的差集  从前面除去后面

  ```properties
  127.0.0.1:6379> SADD k1 q w e r t
  (integer) 5
  127.0.0.1:6379> sadd k2 a d f g e r
  (integer) 6
  127.0.0.1:6379> SDIFF k1 k2
  1) "w"
  2) "t"
  3) "q"
  ```

  - sinter 返回两个集合的交集

  ```properties
  127.0.0.1:6379> SINTER k1 k2
  1) "r"
  2) "e"
  ```

  - sdiffstore  这个类似与sdiff，不同的式，计算出来的结果会村在一个新的集合中
  - sinterstore 类似与sinter，只是将计算出来的交集保存到一个新的集合中
  - sunion 求并集
  - sunionstore 求并集并将结果保存到新的集合中

  ##### hash

  在hash结构中，key式一个字符串，value则是一个key/value的键值对

  - hset 添加 

```properties
127.0.0.1:6379> HSET k1 name 'java'
(integer) 1
```

- hget 获取值

```properties
127.0.0.1:6379> hget k1 name
"java"
```

- hmset 批量设置
- hmget批量获取

```properties
127.0.0.1:6379> HMSET k2 name 'java' age '18' id '1'
OK
127.0.0.1:6379> HMGET k2 name age id 
1) "java"
2) "18"
3) "1"
```

- hdel 删除一个指定的field

```properties
127.0.0.1:6379> HDEL k2 name
(integer) 1
127.0.0.1:6379> hget k2 name
(nil)
```

- hsetnx 默认情况下，如果key和field相同，会覆盖已有的value，hsetnx则不会

```properties
127.0.0.1:6379> hmget k2 age id
1) "18"
2) "1"
127.0.0.1:6379> hset k2 age 88
(integer) 0
127.0.0.1:6379> hmget k2 age id
1) "88"
2) "1"
```

- hvals 获取所有的value

```properties
127.0.0.1:6379> hmget k2 age id
1) "88"
2) "1"
127.0.0.1:6379> HSETNX k2 id 3
(integer) 0
127.0.0.1:6379> hmget k2 age id
1) "88"
2) "1"
```

- hkeys 获取所有的key

```properties
127.0.0.1:6379> HKEYS k2
1) "age"
2) "id"
```

- hgetall 同时获取所有 的key和value

```properties
127.0.0.1:6379> HGETALL k2
1) "age"
2) "88"
3) "id"
4) "1"
```

- hexists 返回fieledd式否存在

```properties
127.0.0.1:6379> HEXISTS k2 age
(integer) 1
```

- hincry  给指定的value自增

```properties
127.0.0.1:6379> HINCRBY k2 id 3
(integer) 4
127.0.0.1:6379> hget k2 id
"4"
```

- hincrbyfloat 可以自增一个浮点数

```properties
127.0.0.1:6379> HINCRBYFLOAT k2 id 0.5
"4.5"
```

- hlen 返回某一个key中value的数量

```properties
127.0.0.1:6379> HLEN k2
(integer) 2
```

- hstrlen 返回某一个field的字符串长度

```properties
127.0.0.1:6379> HSTRLEN k2 id
(integer) 3
```

##### Zset

- zadd  将指定的元素添加到集合中
- zscore返回member的score值

```properties
127.0.0.1:6379> zadd k1 60 c1
(integer) 1
127.0.0.1:6379> ZSCORE k1 c1
"60"
127.0.0.1:6379> zadd k1 80 c2
(integer) 1
127.0.0.1:6379> ZSCORE k1 c2
"80"
```

- zrange返回集合中的一组元素

```properties
127.0.0.1:6379> ZRANGE k1 0 1 withscores
1) "c1"
2) "60"
3) "c2"
4) "80"
```

- zrevrange 返回一组元素，但是是倒序

```properties
127.0.0.1:6379> ZREVRANGE k1 0 1 withscores
1) "c2"
2) "80"
3) "c1"
4) "60"
```

- zcard 返回元素个数

```properties
127.0.0.1:6379> ZCARD k1
(integer) 2
```

- zcount 返回score在某一区间内的元素

```properties
127.0.0.1:6379> ZCOUNT k1 70 85
(integer) 1
127.0.0.1:6379> ZCOUNT k1 0 100
(integer) 2
```

- zrangebyscore 按照score的范围返回元素

```properties
127.0.0.1:6379> ZRANGEBYSCORE k1 79 100 
1) "c2"
127.0.0.1:6379> ZRANGEBYSCORE k1 70 100 withscores
1) "c2"
2) "80"
```

- zrank 返回元素排名从小到大

  ```properties
  127.0.0.1:6379> ZRANK k1 c1
  (integer) 0
  127.0.0.1:6379> ZRANK k1 c2
  (integer) 1
  ```

- zrevrank返回元素排名，从大到小

```properties
127.0.0.1:6379> ZREVRANK k1 c1
(integer) 1
```

- zincrby score自增

```properties
127.0.0.1:6379> ZINCRBY k1 59 c2
"139"
```

- zinterstore

```properties
127.0.0.1:6379> ZADD k2 70 c1 80 c2
(integer) 2
127.0.0.1:6379> ZINTERSTORE k3 2 k1 k2
(integer) 2
127.0.0.1:6379> zrange k3 0 -1 withscores
1) "c1"
2) "130"
3) "c2"
4) "219"
```

- zrem 弹出一个元素

```properties
127.0.0.1:6379> zrem k3 c2
(integer) 1
127.0.0.1:6379> zrange k3 0 -1 withscores
1) "c1"
2) "130"
```

