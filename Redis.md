# Redis

##### 简介：

redis是一个开源的，遵循BSD协议的，是一个高性能的key-value数据库。

##### 用处和意义：

$\textcolor{red}{Redis的数据存放在内存(内存形数据库)}​$，所以速度快但是会受到内存空间限制。MySQL存放在硬盘，在速度上没有Redis快，但是存放的数据量要多的多。所以redis适合放一些频繁使用，比较热的数据，因为是放在内存中，读写速度都非常快，一般会应用在下面一些场景:排行榜、计数器、消息队列推送。目前大多数公司的存储都是mysql + redis，mysql作为主存储，$\textcolor{green}{redis作为辅助存储被用作缓存，加快访问读取的速度，提高性能}​$

##### 优势：

性能极高

支持丰富的数据类型：String(字符串)、Hash(字典）、List(列表)、Set(集合)、Sorted Set(有序集合)。

丰富的特性：支持publish/subscribe，通知，key过期等等特征

原子性：要么成功执行，要么失败，通过MULTI和EXEC指令包起来

##### linux安装redis：

redis安装包地址:https://download.redis.io/releases/redis-6.0.8.tar.gz



