# SpringBoot

@SpringBootApplication 启动类，其本身就是一个javaconfig,即可以充当spring配置文件

**springboot**内部配置加载顺序

1.file:../config/  当前项目下的的/config目录下

2.file:/  当前项目的根目录

3.classpath:config/  classpath下的config目录下

4.classpath:   classpath的根目录(新建项目就是默认这种)

$\textcolor{red}{Actuator监听器：SpringBoot提供的一个模板，实现监控，在浏览器访问}$

localhost:端口号/actuator/+Endpoint ID

**详细信息及描述**

|  Endpoint ID   |                         Description                          |
| :------------: | :----------------------------------------------------------: |
|   auditevent   |       显示应用暴露的审计事件（比如认证进入、订单失败）       |
|      info      |                      显示应用的基本信息                      |
|     health     |                      显示应用的健康状态                      |
|    metrics     |                    显示应用多样的度量信息                    |
|    loggers     |                   显示和修改配置的loggers                    |
|    logfile     | 返回log file中的内容(如果logging.file或者logging.path被设置) |
|   httptrace    |         显示HTTP足迹，最近100个HTTP request／reponse         |
|      env       |                      显示当前的环境特性                      |
|     flyway     |                 显示数据库迁移路径的详细信息                 |
|    shutdown    |                      优雅地逐步关闭应用                      |
|    shutdown    |                      优雅地逐步关闭应用                      |
| scheduledtasks |                     显示应用中的调度任务                     |
|   threaddump   |                       执行一个线程dump                       |
|    heapdump    |                   返回一个GZip压缩的JVM堆                    |

dumpmetrics endpoint展示了你可以追踪的度量，例如jvm内存、cpu使用、jvm线程等

<!--导入依赖-->

```xml
<!--Actuator监听器-->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
    <version>2.6.14</version>
</dependency>
```

------

##### **自定义异常界面**

$\textcolor{Emerald}{在resources下创建public/error  在error目录下定义异常界面，这些异常页面的名称必须为相应的状态码(404,500) 扩展名为html}$   $\textcolor{brown}{报错自己跳转}$

------

##### **多环境选择：相同的代码运行在不同环境  不同环境执行不同类**

```java
package com.example.springboot.service;

public interface SomeService {
    public String service();
}
```

```java
package com.example.springboot.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("pro")
public class SomeServiceImpl implements SomeService{
    @Override
    public String service() {
        return "调用【生产】环境";
    }
}
```

```java
package com.example.springboot.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("dev") // 用该注解
public class OtherServiceImpl implements SomeService{
    @Override
    public String service() {
        return "调用【开发】环境";
    }
}
```

```java
package com.example.springboot.controller;


import com.example.springboot.service.SomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
public class someController {

    @Autowired
    private SomeService service;

    @GetMapping("/some")
    public String someHeader(){
        return service.service();
    }
}
```

$\textcolor{red}{调用哪个环境决定权在 application.yml里面  里面写上}$

$\textcolor{maroon}{spring:profiles: active: pro}$

$\textcolor{green}{用命令行切换:--spring.profiles.active=pro}$

$\textcolor{brown}{虚拟机参数:-Dspring.profiles.active=pro}$

##### **方式一：多个配置文件**

**application.yml**

```properties
spring:
  profiles:
    active: pro
    
info: // 设置info信息 key不能为中文，值可以
  company: www.abc.com
  address: Beijing China
  ArtifaactId: @project.groupId@   // 从pom文件中读取指定属性值

# 修改actuator信息
management:
  server:
    port: 7777
  endpoints:
    web:
      base-path: /base
      exposure:
        include: ["info"]  // 监控终端包含可以查看的信息
        exclude: ["env","health","base"] // 指定不可以查看的终端的信息
        
```

**application-dev.yml**    文件格式必须为application-"xxx"

```java
server:
  port: 5555
```

**application-pro.yml**

```java
server:
  port: 4444
```

##### **方式二：只需要一个配置文件**

```java
spring:
  profiles:
    active: pro

# 修改actuator信息
management:
  server:
    port: 7777
  endpoints:
    web:
      base-path: /base
      exposure:
        include: ["info"]   // 监控终端包含可以查看的信息
        exclude: ["env","health","base"] // 指定不可以查看的终端的信息

---
spring:
  profiles: dev // 指定
server:
  port: 5555

// 加上三个-隔开
---
spring:
  profiles: pro
server:
  port: 4444
```
------

##### **springboot读取配置文件**

方法一：@Value注解

```java
@RestController
public class anotherController {

    @Value("${company.name}")
    private String name;
    
    @Value("${company.age}")
    private int age;

    @GetMapping("/name")
    public String name(){
       return "name:"+name;
    }

    @GetMapping("/age")
    public String age(){
        return "age:"+age;
    }
}
```

```xml
company:
  name: pt
  age: 19
```

方法二：

```java
package com.example.springboot.bean;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component  // 生命周期交给spring容器管理
@PropertySource(value = "classpath:students.properties",encoding = "UTF-8") // 指定读取的配置文件
@ConfigurationProperties("abc.student") // 指定读取属性前缀
@Data // 自动带有get set方法
public class Student {
    private String name;
    private int age;
    private double score;
}
```

```java
@RestController
public class anotherController {

    @Autowired
    private Student student;

    @GetMapping("/student")
    public String student(){
        return "student="+student;
    }
}
```

读取list<String>类型

```java
package com.example.springboot.bean;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component  // 生命周期交给spring容器管理
@PropertySource(value = "classpath:students.properties",encoding = "UTF-8") // 指定读取的配置文件
@ConfigurationProperties("country") // 指定读取属性前缀
@Data // 自动带有get set方法
public class City {
    private List<String> city;
}
<!------类里面的变量名要与配置文件一致---------->
----------------------------------------配置文件-------------------------------
country.city[0]=武汉
country.city[1]=上海
country.city[2]=北京
```

读取list<Object>类型

```java
package com.example.springboot.bean;

import lombok.Data;

@Data
public class Depart {
   private String name;
   private int number;
   private String address;

}
```

```java
package com.example.springboot.bean;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.List;
@Component  // 生命周期交给spring容器管理
@PropertySource(value = "classpath:students.properties",encoding = "UTF-8") // 指定读取的配置文件
@ConfigurationProperties("company") // 指定读取属性前缀
@Data
public class Company {
    private List<Depart> pt;
}



------------------------------------配置文件------------------------------------------
 company.pt[0].name=研发部
company.pt[0].number=30
company.pt[0].address=开发中心
company.pt[1].name=销售部
company.pt[1].number=60
company.pt[1].address=销售大楼
company.pt[2].name=运维部
company.pt[2].number=200
company.pt[2].address=运维仓库

----------------------------------测试方法----------------------------------------------
 @Autowired
    private Company company;
    @GetMapping("/company")
    public String company(){
        return "city="+company.getPt();
    }
```

------

@Value获取值与@ConfigurationProperties获取值较

|                    | @ConfigurationProperties |   @Value   |
| :----------------: | :----------------------: | :--------: |
|        功能        | 批量注入配置文件中的属性 | 一个个指定 |
| 松散绑定(松散语法) |           支持           |   不支持   |
|        SpEl        |          不支持          |    支持    |
|   JSR303数据校验   |           支持           |   不支持   |
|    复杂类型封装    |           支持           |    支持    |

如果说，只是在某个业务中逻辑中需要获取一下配置文件中的某个值，用@Value

如果说，专门写了一个javaBean来和配置文件映射，用@ConfigurationProperties

$\textcolor{red}{@ConfigurationProperties("company") // 指定读取属性前缀}$

$\textcolor{green}{@PropertySource(value = "classpath:students.properties",encoding = "UTF-8") // 指定读取的配置文件}$

$\textcolor{red}{@ImportResource(locations = {""})导入spring的配置文件，让其生效}$

$\textcolor{green}{@Configuration用在类前面，指明该类为配置类}$

$\textcolor{red}{@Bean给容器添加组件(如bean)}$

##### JSR303校验:使用步骤：

Step1：
　　在相关的 Bean 上标注需要处理的注解，并指定需要提示的信息（若不指定，会从默认配置文件中读取默认的信息）。

Step2：
　　在相关的方法上，使用 @Valid 注解（或者 @Validated 指定组名）标记需要被校验的数据，否则会不生效。
注意：
　　检测到数据异常后，系统会向外抛出异常，如果做了统一异常处理，可以根据 postman 测试的结果，找到控制台打印出的 相应的异常，并处理。

Step3：
　　处理异常。使用 BindingResult 可以获取到检测结果，然后进行处理。
　　也可以使用 全局统一异常 处理（@RestControllerAdvice 与 @ExceptionHandler），处理检测结果。

##### 配置文件占位符

```properties
${random.value} ${random.int}  ${random.int[10]}  ${random.int[100,1000]}
```

也可以获取之前配置的值，如果获取不到，可以用冒号:来指定值

```properties
person.name=${person.age}
person.age=${random.int}
// 获取不到
person.name=${person.age:27}
```

##### springboot项目中使用jsp

$\textcolor{Red}{默认支持的前端页面是thymeleaf，如果要使用jsp页面，则需要完成以下几个步骤}:$

1. 在main目录下创建webapp,并把它设置为web资源文件夹，用来装载jsp文件

2. 在pom.xml文件中引入SpringBoot内嵌Tomcat对jsp的解析包

   ```xml
   <!--内置tomcat对Jsp支持的依赖，用于编译Jsp-->
       <dependency>
           <groupId>org.apache.tomcat.embed</groupId>
           <artifactId>tomcat-embed-jasper</artifactId>
       </dependency>

       <!--jstl的支持,c标签-->
       <dependency>
           <groupId>javax.servlet</groupId>
           <artifactId>jstl</artifactId>
           <scope>compile</scope>
       </dependency>

       <dependency>
           <groupId>javax.servlet.jsp</groupId>
           <artifactId>javax.servlet.jsp-api</artifactId>
           <version>2.3.1</version>
       </dependency>
   ```

3.在pom.xml文件的<build></build>中手动指定jsp最后编译的路径

SpringBoot集成jsp编译jsp的路径是规定好的位置：META-INF/resources

<build>

```xml
<resources>
    <resource>
        <!--源文件夹-->
        <directory>scr/main/webapp</directory>
        <!--指定编译到META-INF/resources-->
        <targetPath>META-INF/resources</targetPath>
        <!--指定源文件夹中哪个资源要进行编译-->
        <includes>
            <include>**/*.*</include>
        </includes>
    </resource>
</resources>
```

</build>

4.可以在Application.propertices文件中配置视图解析器

```xml
# SpringBoot视图配置
spring.mvc.view.prefix=/
spring.mvc.view.suffix=.jsp
```
------

##### **springboot整合junit**

1.导入依赖，一般创建的spring boot项目自动导入这个依赖了

```xml
<dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
 </dependency>
```

2.src/test/java下新建测试类,在类前面加上注解:

```java
@RunWith(SpringRunner.class)//以Spring启动类的方式运行测试类
@SpringBootTest(classes= MySpringBootApplication.class)//测试启动类入口，这里填写自己的

public class TestJunit {
@AutoWired
private UserMapper userMapper;//如有必要，可以直接注入mapper
@Test//测试方法入口注解
public void testJunit(){
    System.out.println("我是单元测试");
    //userMapper.queryAllUser();//如有必要，可直接调用mapper方法测试sql
}
}
```
若测试类所在包下的包名若与启动类所在包名一样，则注解@SpringBootText后面括号里面可以省略;

##### springboot集成mybatis

1.首先导入所需要依赖的包:

```xml
<!--springboot整合mybatis-->
<dependency>
    <groupId>org.mybatis.spring.boot</groupId>
    <artifactId>mybatis-spring-boot-starter</artifactId>
    <version>2.1.2</version>
</dependency>
```

以及  mysql-connector-java-8.0.28

2.可以使用插件(mybatis逆向工程生成映射文件及接口)

**注意:**$\textcolor{Red}{生成的xml文件如果在java目录下，要用  .  代替  /}$

```properties
# 注册映射文件
mybatis:
  mapper-locations: classpath:com.example.springboot.dao.*.xml
  type-aliases-package: com.example.springboot.entity
# 注册实体类别名

#注册数据源
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/springboot
    password: 3548297839
    driver-class-name: com.mysql.cj.jdbc.Driver
    username: root
```

要在生成的接口上类上添加@Mapper注解,注册所需要的数据源  url password driverclass  username

同时要在pom.xml文件中指定映射文件为资源目录

```xml
<!--注册dao为资源目录-->
<resource>
    <directory>src/main/java/</directory>
    <includes>
        <include>**/*.xml</include>
    </includes>
</resource>
```

3.测试

在接口类上添加注解，方便在controller类里面通过注入直接调用

测试代码如下:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.example.springboot.dao.StudentMapper">
    <resultMap id="BaseResultMap" type="com.example.springboot.entity.Student">
        <result column="id" jdbcType="INTEGER" property="id"/>
        <result column="name" jdbcType="VARCHAR" property="name"/>
        <result column="age" jdbcType="INTEGER" property="age"/>
    </resultMap>
    <insert id="insert" parameterType="com.example.springboot.entity.Student">
        insert into student (id, name, age)
        values (#{id,jdbcType=INTEGER}, #{name,jdbcType=VARCHAR}, #{age,jdbcType=INTEGER})
    </insert>
</mapper>
```

```java
package com.example.springboot.dao;

import com.example.springboot.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository("student")
public interface StudentMapper {

    int insert(Student record);


}
```

```java
@RequestMapping("/insert")
public String text_2(){
    System.out.println("执行插入....");
    Student student=new Student();
    student.setId(3);
    student.setAge(33);
    student.setName("王五");
    System.out.println(student);
    studentMapper.insert(student);
    return "插入成功";
}
```
##### **springboot整合redis**

1.导入依赖

```xml
<!-- 引入 redis 依赖 -->

<dependency>

    <groupId>org.springframework.boot</groupId>

    <artifactId>spring-boot-starter-data-redis</artifactId>

    <version>1.5.7.RELEASE</version>

</dependency>

```

2.资源文件application.properties中增加Redis相关配置

```properties
############################################################
# REDIS 配置
############################################################
spring.redis.database=0
spring.redis.host=127.0.0.1
spring.redis.port=6379
spring.redis.password=
spring.redis.jedis.pool.max-active=8
spring.redis.jedis.pool.max-wait=-1
spring.redis.jedis.pool.max-idle=10
spring.redis.jedis.pool.min-idle=2
spring.redis.timeout=6000
```

3.封装Json工具类,这个工具类比较简单，封装操作redisTemplate的实现类。这个工具类只是简单的封装了StringRedisTemplate，其他相关的数据类型大家可以根据自己的需要自行扩展。

```java
package com.weiz.utils;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisOperator {

    <!--重点-->
   @Autowired
   private StringRedisTemplate redisTemplate;
   
   // Key（键），简单的key-value操作

   /**
    * 实现命令：TTL key，以秒为单位，返回给定 key的剩余生存时间(TTL, time to live)。
    * 
    * @param key
    * @return
    */
   public long ttl(String key) {
      return redisTemplate.getExpire(key);
   }
   
   /**
    * 实现命令：expire 设置过期时间，单位秒
    * 
    * @param key
    * @return
    */
   public void expire(String key, long timeout) {
      redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
   }
   
   /**
    * 实现命令：INCR key，增加key一次
    * 
    * @param key
    * @return
    */
   public long incr(String key, long delta) {
      return redisTemplate.opsForValue().increment(key, delta);
   }

   /**
    * 实现命令：KEYS pattern，查找所有符合给定模式 pattern的 key
    */
   public Set<String> keys(String pattern) {
      return redisTemplate.keys(pattern);
   }

   /**
    * 实现命令：DEL key，删除一个key
    * 
    * @param key
    */
   public void del(String key) {
      redisTemplate.delete(key);
   }

   // String（字符串）

   /**
    * 实现命令：SET key value，设置一个key-value（将字符串值 value关联到 key）
    * 
    * @param key
    * @param value
    */
   public void set(String key, String value) {
      redisTemplate.opsForValue().set(key, value);
   }

   /**
    * 实现命令：SET key value EX seconds，设置key-value和超时时间（秒）
    * 
    * @param key
    * @param value
    * @param timeout
    *            （以秒为单位）
    */
   public void set(String key, String value, long timeout) {
      redisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
   }

   /**
    * 实现命令：GET key，返回 key所关联的字符串值。
    * 
    * @param key
    * @return value
    */
   public String get(String key) {
      return (String)redisTemplate.opsForValue().get(key);
   }

   // Hash（哈希表）

   /**
    * 实现命令：HSET key field value，将哈希表 key中的域 field的值设为 value
    * 
    * @param key
    * @param field
    * @param value
    */
   public void hset(String key, String field, Object value) {
      redisTemplate.opsForHash().put(key, field, value);
   }

   /**
    * 实现命令：HGET key field，返回哈希表 key中给定域 field的值
    * 
    * @param key
    * @param field
    * @return
    */
   public String hget(String key, String field) {
      return (String) redisTemplate.opsForHash().get(key, field);
   }

   /**
    * 实现命令：HDEL key field [field ...]，删除哈希表 key 中的一个或多个指定域，不存在的域将被忽略。
    * 
    * @param key
    * @param fields
    */
   public void hdel(String key, Object... fields) {
      redisTemplate.opsForHash().delete(key, fields);
   }

   /**
    * 实现命令：HGETALL key，返回哈希表 key中，所有的域和值。
    * 
    * @param key
    * @return
    */
   public Map<Object, Object> hgetall(String key) {
      return redisTemplate.opsForHash().entries(key);
   }

   // List（列表）

   /**
    * 实现命令：LPUSH key value，将一个值 value插入到列表 key的表头
    * 
    * @param key
    * @param value
    * @return 执行 LPUSH命令后，列表的长度。
    */
   public long lpush(String key, String value) {
      return redisTemplate.opsForList().leftPush(key, value);
   }

   /**
    * 实现命令：LPOP key，移除并返回列表 key的头元素。
    * 
    * @param key
    * @return 列表key的头元素。
    */
   public String lpop(String key) {
      return (String)redisTemplate.opsForList().leftPop(key);
   }

   /**
    * 实现命令：RPUSH key value，将一个值 value插入到列表 key的表尾(最右边)。
    * 
    * @param key
    * @param value
    * @return 执行 LPUSH命令后，列表的长度。
    */
   public long rpush(String key, String value) {
      return redisTemplate.opsForList().rightPush(key, value);
   }

}
```

##### **springboot对日志的控制**

对日志的输出控制：可以在application.yml文件中配配置

```yaml
logging:
  pattern:
    console: logs-%level %msg%n
  level:
    root: warn
    com.example.springboot.mapper: debug
```

也可以单独在resource文件夹下新创一个文件logback.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>  
  
<!-- 从高到地低 OFF 、 FATAL 、 ERROR 、 WARN 、 INFO 、 DEBUG 、 TRACE 、 ALL -->  
<!-- 日志输出规则  根据当前ROOT 级别，日志输出时，级别高于root默认的级别时  会输出 -->  
<!-- 以下  每个配置的 filter 是过滤掉输出文件里面，会出现高级别文件，依然出现低级别的日志信息，通过filter 过滤只记录本级别的日志-->  
  
  
<!-- 属性描述 scan：性设置为true时，配置文件如果发生改变，将会被重新加载，默认值为true scanPeriod:设置监测配置文件是否有修改的时间间隔，如果没有给出时间单位，默认单位是毫秒。当scan为true时，此属性生效。默认的时间间隔为1分钟。   
    debug:当此属性设置为true时，将打印出logback内部日志信息，实时查看logback运行状态。默认值为false。 -->  
<configuration scan="true" scanPeriod="60 seconds" debug="false">  
    <!-- 定义日志文件 输入位置 -->  
    <property name="log_dir" value="/logs/ev_cmdb" />  
    <!-- 日志最大的历史 30天 -->  
    <property name="maxHistory" value="30"/>  
  
    <!-- ConsoleAppender 控制台输出日志 -->  
    <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">  
        <!-- 对日志进行格式化 -->  
        <encoder>  
            <pattern>%d{yyyy-MM-dd HH:mm:ss} [%thread] %-5level %logger -%msg%n</pattern>  
        </encoder>  
    </appender>     
    <!-- ERROR级别日志 -->  
    <!-- 滚动记录文件，先将日志记录到指定文件，当符合某个条件时，将日志记录到其他文件 RollingFileAppender-->  
    <appender name="ERROR" class="ch.qos.logback.core.rolling.RollingFileAppender">  
        <!-- 过滤器，只记录WARN级别的日志 -->  
        <filter class="ch.qos.logback.classic.filter.LevelFilter">  
            <level>ERROR</level>  
            <onMatch>ACCEPT</onMatch>  
            <onMismatch>DENY</onMismatch>  
        </filter>  
        <!-- 最常用的滚动策略，它根据时间来制定滚动策略.既负责滚动也负责出发滚动 -->  
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">  
            <!--日志输出位置  可相对、和绝对路径 -->  
            <fileNamePattern>${log_dir}/%d{yyyy-MM-dd}/error-log.log</fileNamePattern>  
            <!-- 可选节点，控制保留的归档文件的最大数量，超出数量就删除旧文件假设设置每个月滚动，且<maxHistory>是6，  
            则只保存最近6个月的文件，删除之前的旧文件。注意，删除旧文件是，那些为了归档而创建的目录也会被删除-->  
            <maxHistory>${maxHistory}</maxHistory>  
        </rollingPolicy>  
          
        <!-- 按照固定窗口模式生成日志文件，当文件大于20MB时，生成新的日志文件。窗口大小是1到3，当保存了3个归档文件后，将覆盖最早的日志。   
        <rollingPolicy class="ch.qos.logback.core.rolling.FixedWindowRollingPolicy">     
          <fileNamePattern>${log_dir}/%d{yyyy-MM-dd}/.log.zip</fileNamePattern>     
          <minIndex>1</minIndex>     
          <maxIndex>3</maxIndex>     
        </rollingPolicy>   -->  
        <!-- 查看当前活动文件的大小，如果超过指定大小会告知RollingFileAppender 触发当前活动文件滚动   
        <triggeringPolicy class="ch.qos.logback.core.rolling.SizeBasedTriggeringPolicy">     
            <maxFileSize>5MB</maxFileSize>     
        </triggeringPolicy>   -->  
          
        <encoder>  
            <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger - %msg%n</pattern>  
        </encoder>  
    </appender>   
    <!-- WARN级别日志 appender -->  
    <appender name="WARN" class="ch.qos.logback.core.rolling.RollingFileAppender">  
        <!-- 过滤器，只记录WARN级别的日志 -->  
        <filter class="ch.qos.logback.classic.filter.LevelFilter">  
            <level>WARN</level>  
            <onMatch>ACCEPT</onMatch>  
            <onMismatch>DENY</onMismatch>  
        </filter>  
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">  
            <!-- 按天回滚 daily -->  
            <fileNamePattern>${log_dir}/%d{yyyy-MM-dd}/warn-log.log  
            </fileNamePattern>  
            <!-- 日志最大的历史 60天 -->  
            <maxHistory>${maxHistory}</maxHistory>  
        </rollingPolicy>  
        <encoder>  
            <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger - %msg%n</pattern>  
        </encoder>  
    </appender>    
    <!-- INFO级别日志 appender -->  
    <appender name="INFO" class="ch.qos.logback.core.rolling.RollingFileAppender">  
        <!-- 过滤器，只记录INFO级别的日志 -->  
        <filter class="ch.qos.logback.classic.filter.LevelFilter">  
            <level>INFO</level>  
            <onMatch>ACCEPT</onMatch>  
            <onMismatch>DENY</onMismatch>  
        </filter>  
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">  
            <!-- 按天回滚 daily -->  
            <fileNamePattern>${log_dir}/%d{yyyy-MM-dd}/info-log.log  
            </fileNamePattern>  
            <!-- 日志最大的历史 60天 -->  
            <maxHistory>${maxHistory}</maxHistory>  
        </rollingPolicy>  
        <encoder>  
            <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger - %msg%n</pattern>  
        </encoder>  
    </appender>    
    <!-- DEBUG级别日志 appender -->  
    <appender name="DEBUG" class="ch.qos.logback.core.rolling.RollingFileAppender">  
        <!-- 过滤器，只记录DEBUG级别的日志 -->  
        <filter class="ch.qos.logback.classic.filter.LevelFilter">  
            <level>DEBUG</level>  
            <onMatch>ACCEPT</onMatch>  
            <onMismatch>DENY</onMismatch>  
        </filter>  
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">  
            <!-- 按天回滚 daily -->  
            <fileNamePattern>${log_dir}/%d{yyyy-MM-dd}/debug-log.log  
            </fileNamePattern>  
            <!-- 日志最大的历史 60天 -->  
            <maxHistory>${maxHistory}</maxHistory>  
        </rollingPolicy>  
        <encoder>  
            <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger - %msg%n</pattern>  
        </encoder>  
    </appender>        
    <!-- TRACE级别日志 appender -->  
    <appender name="TRACE" class="ch.qos.logback.core.rolling.RollingFileAppender">  
        <!-- 过滤器，只记录ERROR级别的日志 -->  
        <filter class="ch.qos.logback.classic.filter.LevelFilter">  
            <level>TRACE</level>  
            <onMatch>ACCEPT</onMatch>  
            <onMismatch>DENY</onMismatch>  
        </filter>  
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">  
            <!-- 按天回滚 daily -->  
            <fileNamePattern>${log_dir}/%d{yyyy-MM-dd}/trace-log.log  
            </fileNamePattern>  
            <!-- 日志最大的历史 60天 -->  
            <maxHistory>${maxHistory}</maxHistory>  
        </rollingPolicy>  
        <encoder>  
            <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger - %msg%n</pattern>  
        </encoder>  
    </appender>  
    <logger name="java.sql.PreparedStatement" value="DEBUG" />    
    <logger name="java.sql.Connection" value="DEBUG" />    
    <logger name="java.sql.Statement" value="DEBUG" />    
    <logger name="com.ibatis" value="DEBUG" />    
    <logger name="com.ibatis.common.jdbc.SimpleDataSource" value="DEBUG" />    
    <logger name="com.ibatis.common.jdbc.ScriptRunner" level="DEBUG"/>    
    <logger name="com.ibatis.sqlmap.engine.impl.SqlMapClientDelegate" value="DEBUG" />         
    <!-- root级别   DEBUG -->  
    <root level="debug">  
        <!-- 控制台输出 -->  
        <appender-ref ref="STDOUT" />  
        <!-- 文件输出 -->  
        <appender-ref ref="ERROR" />  
        <appender-ref ref="INFO" />  
        <appender-ref ref="WARN" />  
        <appender-ref ref="DEBUG" />  
        <appender-ref ref="TRACE" />  
    </root>  
</configuration> 
```
##### SpringBoot的自动装配

思考：springboot是如何获取到bean的?

```java
@SpringBootApplication
public class Application {

    public static void main(String[] args) {

        ConfigurableApplicationContext s=SpringApplication.run(Application.class, args);
        System.out.println(s.getBean("student"));
    }

}
```

$\textcolor{red}{启动类是有返回值的，返回的是spring的ioc容器，可以用get获取bean}$

springboot导入坐标(例如:redis坐标),若导入加载后，则有redis的bean

如何创建一个bean？

先创建一个类User，在创建一个测试类UserText，

$\textcolor{green}{该类添加一个注解@Configuration,该类有一个方法，该方法返回值为一个类，该方法名就是对应的bean的名称}$

```java
package com.example.springboot.service;

public class User {
    public void text(){
        System.out.println("User.....");
    }
}
```

```java
package com.example.springboot.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TextUser {

    @Bean
    public User user(){
        return new User();
    }
}
```

```java
package com.example.springboot;

import com.example.springboot.service.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {

        ConfigurableApplicationContext s=SpringApplication.run(Application.class, args);
        User user= (User) s.getBean("user");
        System.out.println(s.getBean("user"));
        user.text();
    }

}
```

@Conditional创建bean过程中能否创建bean的条件

一个类实现 implements Condition 重写方法  若实现的方法返回值为true,则运行创建bean

```java
package com.example.springboot.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TextUser {

    @Bean
    @Conditional(UserCondition.class)  <!--括号里是实现Condition的那个类-->
    public User user(){
        return new User();
    }
}
```

```java
public class UserCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        return false;
    }
}
```
SpringBoot中提供了很多@Enable开头的注解，这些注解都是动态启用某些功能的，其底层是@import注解导入某些配置类，实现bean的动态加载

@Import注解：加载类，这些类都会被spring容器创建，并存入ioc容器,有四种用法

1.导入bean

```java
@Import(User.class)
@SpringBootApplication
public class ImportConfigApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext ac=SpringApplication.run(ImportConfigApplication.class, args);
        User user=ac.getBean(User.class);
        System.out.println(user);
    }

}
```

2.导入配置类

```java
package com.example.import_config.bean;

import org.springframework.context.annotation.Bean;

public class UserConfig {
    @Bean
    public User user(){
        return new User();
    }
}
```

```java
package com.example.import_config;

import com.example.import_config.bean.User;
import com.example.import_config.bean.UserConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;

//@Import(User.class)
@Import(UserConfig.class)
@SpringBootApplication
public class ImportConfigApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext ac=SpringApplication.run(ImportConfigApplication.class, args);
        User user= (User) ac.getBean("user");
        System.out.println(user);
    }

}
```

3.导入importselector实现类，一般用于配置文件中的类

```java
public class UserSelect implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{"com.example.import_config.bean.User"};
    }
}
```

```java
@Import(UserSelect.class)
@SpringBootApplication
public class ImportConfigApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext ac=SpringApplication.run(ImportConfigApplication.class, args);
        User user= (User) ac.getBean(User.class);
        System.out.println(user);
    }

}
```

4.ImportBeanDefinitionRegistrar的实现类

```java
public class Mybean implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry, BeanNameGenerator importBeanNameGenerator) {
        registry.registerBeanDefinition("user",BeanDefinitionBuilder.rootBeanDefinition(User.class)
                .getBeanDefinition());
    }
}
```

```java
@Import(Mybean.class)
@SpringBootApplication
public class ImportConfigApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext ac=SpringApplication.run(ImportConfigApplication.class, args);
        User user= (User) ac.getBean("user");
        System.out.println(user);
    }

}
```

##### springboot的内置服务器

四种：tomcat jetty netty undertom

新建的springboot项目默认导入的是tomcat 要切换服务器的话，取出依赖，添加另一种服务依赖即可

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

```java
org.springframework.boot.autoconfigure.web.embedded
```

可以看这个包下，加入@condition注解判断使用

```java
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.springframework.boot.autoconfigure.web.embedded;

import io.undertow.Undertow;
import org.apache.catalina.startup.Tomcat;
import org.apache.coyote.UpgradeProtocol;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.util.Loader;
import org.eclipse.jetty.webapp.WebAppContext;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.xnio.SslClientAuthMode;
import reactor.netty.http.server.HttpServer;

@AutoConfiguration
@ConditionalOnWebApplication
@EnableConfigurationProperties({ServerProperties.class})
public class EmbeddedWebServerFactoryCustomizerAutoConfiguration {
    public EmbeddedWebServerFactoryCustomizerAutoConfiguration() {
    }

    @Configuration(
        proxyBeanMethods = false
    )
    @ConditionalOnClass({HttpServer.class})
    public static class NettyWebServerFactoryCustomizerConfiguration {
        public NettyWebServerFactoryCustomizerConfiguration() {
        }

        @Bean
        public NettyWebServerFactoryCustomizer nettyWebServerFactoryCustomizer(Environment environment, ServerProperties serverProperties) {
            return new NettyWebServerFactoryCustomizer(environment, serverProperties);
        }
    }

    @Configuration(
        proxyBeanMethods = false
    )
    @ConditionalOnClass({Undertow.class, SslClientAuthMode.class})
    public static class UndertowWebServerFactoryCustomizerConfiguration {
        public UndertowWebServerFactoryCustomizerConfiguration() {
        }

        @Bean
        public UndertowWebServerFactoryCustomizer undertowWebServerFactoryCustomizer(Environment environment, ServerProperties serverProperties) {
            return new UndertowWebServerFactoryCustomizer(environment, serverProperties);
        }
    }

    @Configuration(
        proxyBeanMethods = false
    )
    @ConditionalOnClass({Server.class, Loader.class, WebAppContext.class})
    public static class JettyWebServerFactoryCustomizerConfiguration {
        public JettyWebServerFactoryCustomizerConfiguration() {
        }

        @Bean
        public JettyWebServerFactoryCustomizer jettyWebServerFactoryCustomizer(Environment environment, ServerProperties serverProperties) {
            return new JettyWebServerFactoryCustomizer(environment, serverProperties);
        }
    }

    @Configuration(
        proxyBeanMethods = false
    )
    @ConditionalOnClass({Tomcat.class, UpgradeProtocol.class})
    public static class TomcatWebServerFactoryCustomizerConfiguration {
        public TomcatWebServerFactoryCustomizerConfiguration() {
        }

        @Bean
        public TomcatWebServerFactoryCustomizer tomcatWebServerFactoryCustomizer(Environment environment, ServerProperties serverProperties) {
            return new TomcatWebServerFactoryCustomizer(environment, serverProperties);
        }
    }
}
```
##### springboot使用admin-server与admin-client

$\textcolor{red}{创建两个module，server端(实现springweb与Ops spring boot admin(server)),}$

$\textcolor{red}{client端(实现springweb与Ops spring boot admin(server))}$

在服务端的启动类加上$\textcolor{red}{@EnableAdminServer}$，配置端口号

在客户端配置服务端的端口号

```properties
spring.boot.admin.client.url=http://localhost:2000
management.endpoints.web.exposure.include=*
management.endpoint.health.show-details=always
```

直接访问服务端端口号就可以查看客户端的多项配置

------



##### SpringBoot对静态资源的映射访问

```java
public void addResourceHandlers(ResourceHandlerRegistry registry) {
    if (!this.resourceProperties.isAddMappings()) {
        logger.debug("Default resource handling disabled");
    } else {
        this.addResourceHandler(registry, "/webjars/**", "classpath:/META-INF/resources/webjars/");
        this.addResourceHandler(registry, this.mvcProperties.getStaticPathPattern(), (registration) -> {
            registration.addResourceLocations(this.resourceProperties.getStaticLocations());
            if (this.servletContext != null) {
                ServletContextResource resource = new ServletContextResource(this.servletContext, "/");
                registration.addResourceLocations(new Resource[]{resource});
            }

        });
    }
}
```

所以的/webjars/ 都去"classpath:/META-INF/resources/webjars/"寻找

webjars以jar包的形式引入静态资源

![](D:\everything\linux截图\webjars.png)

访问:http://localhost:8800/

自己的静态资源如何访问?

**Spring Boot 默认将 /** 所有访问映射到以下目录：**

```properties
classpath:/static
classpath:/public
classpath:/resources
classpath:/META-INF/resources
```

接下来，在`main/resources`下新建`static`、`public`和`resources`三个文件夹，分别放入`a.png`、`b.png`和`c.png`三张图片，如下：

![](D:\everything\截图\a.png)

启动项目，分别访问：

http://localhost:8083/a.png
http://localhost:8083/b.png
http://localhost:8083/c.png

发现都能正常访问相应的图片资源。那么说明，`Spring Boot` 默认会挨个从 `public`、`resources`和`static` 里面找是否存在相应的资源，如果有则直接返回。

追溯源码发现，在`WebMvcAutoConfiguration`类中，有如下代码：

```java
public void addResourceHandlers(ResourceHandlerRegistry registry) {
            if(!this.resourceProperties.isAddMappings()) {
                logger.debug("Default resource handling disabled");
            } else {
                Duration cachePeriod = this.resourceProperties.getCache().getPeriod();
                CacheControl cacheControl = this.resourceProperties.getCache().getCachecontrol().toHttpCacheControl();
                if(!registry.hasMappingForPattern("/webjars/**")) {
                    this.customizeResourceHandlerRegistration(registry.addResourceHandler(new String[]{"/webjars/**"}).addResourceLocations(new String[]{"classpath:/META-INF/resources/webjars/"}).setCachePeriod(this.getSeconds(cachePeriod)).setCacheControl(cacheControl));
                }
// 此处做的就是静态资源文件夹映射
                String staticPathPattern = this.mvcProperties.getStaticPathPattern();
                if(!registry.hasMappingForPattern(staticPathPattern)) {
                    this.customizeResourceHandlerRegistration(registry.addResourceHandler(new String[]{staticPathPattern}).addResourceLocations(getResourceLocations(this.resourceProperties.getStaticLocations())).setCachePeriod(this.getSeconds(cachePeriod)).setCacheControl(cacheControl));
                }

            }
        }
```

通过追溯`staticPathPattern`，在`ResourceProperties`类中，发现如下常量信息被设置：

```java
private static final String[] CLASSPATH_RESOURCE_LOCATIONS = new String[]{"classpath:/META-INF/resources/", "classpath:/resources/", "classpath:/static/", "classpath:/public/"};
```

到此，就可以发现，这就是`Spring Boot`为我们提供的默认静态资源映射，那么自定义映射规则的话，继承`WebMvcConfigurer`即可。如下：

```java
@Configuration
public class MyWebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 将/static/**访问映射到classpath:/mystatic/
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/mystatic/");
    }
}
```

在`main/resources`下新建`mystatic`文件夹，并放入`d.png`图片。启动项目，访问如下链接：
`http://localhost:8083/static/d.png`发现，可以正常请求`d.png`图片。

在application.properties中进行  配置配置静态资源访问路径

在application.properties中配置如下：

```properties
spring.mvc.static-path-pattern=/mystatic/**
```

重启项目，再访问静态资源，需要以mystatic开头，如下：
http://localhost:8083/mystatic/a.png
这样，仍可以访问之前的a b c三张图片。如果按照之前http://localhost:8083/a.png就不能再访问到了。

配置静态资源目录

在application.properties中，增加如下配置：

```properties
# 配置静态资源访问前缀
spring.mvc.static-path-pattern=/mystatic/**
# 配置静态资源路径，默认配置失效
spring.resources.static-locations[0]=classpath:/mystatic
spring.resources.static-locations[1]=classpath:/public
```

重启项目，访问：
http://localhost:8083/mystatic/a.png 发现可以正常访问，同理，mystatic和static中静态资源都可以正常访问。
但当访问resources和static里静态资源时，就会404报错，访问不到了，这个是因为配置文件中如果进行了静态资源路径的配置，那么默认的配置就失效了。

------



##### 模板引擎(Thymeleaf)

**1.是什么**　　

简单说， Thymeleaf 是一个跟 Velocity、FreeMarker 类似的模板引擎，它可以完全替代 JSP 。

**2.feature**

1.Thymeleaf 在有网络和无网络的环境下皆可运行，即它可以让美工在浏览器查看页面的静态效果，也可以让程序员在服务器查看带数据的动态页面效果。这是由于它支持 html 原型，然后在 html 标签里增加额外的属性来达到模板+数据的展示方式。浏览器解释 html 时会忽略未定义的标签属性，所以 thymeleaf 的模板可以静态地运行；当有数据返回到页面时，Thymeleaf 标签会动态地替换掉静态内容，使页面动态显示。

2.Thymeleaf 开箱即用的特性。它提供标准和spring标准两种方言，可以直接套用模板实现JSTL、 OGNL表达式效果，避免每天套模板、该jstl、改标签的困扰。同时开发人员也可以扩展和创建自定义的方言。

3.Thymeleaf 提供spring标准方言和一个与 SpringMVC 完美集成的可选模块，可以快速的实现表单绑定、属性编辑器、国际化等功能。

引入依赖:

springboot直接引入：

```xml
<dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-thymeleaf</artifactId>
</dependency>
```

非springboot项目使用如下依赖:

```xml
<dependency>
    <groupId>org.thymeleaf</groupId>
    <artifactId>thymeleaf</artifactId>
    <version>2.1.4</version>
</dependency>
```

默认的模板映射路径是：**src/main/resources/templates，**

springboot1.4之后，可以使用thymeleaf3来提高效率，并且解决标签闭合问题，配置方式：

```xml
<properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <thymeleaf.version>3.0.0.RELEASE</thymeleaf.version>
    <thymeleaf-layout-dialect.version>2.0.0</thymeleaf-layout-dialect.version>
    <java.version>1.8</java.version>
  </properties>
```

使用：若要使用Thymeleaf语法，首先要声明名称空间

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
```

例如:

```java
@Data
public class User {   /// 实体类
    private String name;
    private String password;   
}
```

```java
@Controller
@Slf4j
public class IndexController {

    @GetMapping(value = {"", "/"})
    public ModelAndView index(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();

        User user = (User) request.getSession().getAttribute("user");
        if (ObjectUtil.isNull(user)) {
            mv.setViewName("redirect:/user/login");
        } else {
            mv.setViewName("page/index");
            mv.addObject(user);
        }

        return mv;
    }
}
```

```java
@Controller
@RequestMapping("/user")
@Slf4j
public class UserController {
    @PostMapping("/login")
    public ModelAndView login(User user, HttpServletRequest request) {
        System.out.println(user);
        ModelAndView mv = new ModelAndView();
        mv.addObject(user);
        mv.setViewName("redirect:/");
        request.getSession().setAttribute("user", user);
        return mv;
    }

    @GetMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("page/login");
    }
}
```

分析代码执行过程:

启动程序,访问localhost:8080/demo，走@GetMapping(value = {"", "/"})，此时session取不到user,重定到/login  login返回视图到登录页面，输入name ,password$\textcolor{red}{注意：此时标签名的属性名与User类的属性名一致，自动给User属性名分别赋值}$,此时将user对象添加到session中，mv.setViewName("redirect:/")，此时session能取到,不为null， mv.setViewName("page/index"); mv.addObject(user);

------



##### springboot国际化

$\textcolor{green}{详细见工程}$==D:\everything\coding\springboot_study\internation_demo==

首先需要了解的是:Spring Boot为Spring MVC提供了自动配置，适用于大多数应用程序。

自动配置在Spring的默认值之上添加了以下功能：

- 包含`ContentNegotiatingViewResolver`和`BeanNameViewResolver` beans。
- 支持提供静态资源，包括对WebJars的支持（ [本文档稍后介绍](https://www.springcloud.cc/spring-boot.html#boot-features-spring-mvc-static-content)））。
- 自动注册`Converter`，`GenericConverter`和`Formatter` beans。
- 支持`HttpMessageConverters`（ [本文档稍后部分](https://www.springcloud.cc/spring-boot.html#boot-features-spring-mvc-message-converters)）。
- 自动注册`MessageCodesResolver`（ [本文档后面部分](https://www.springcloud.cc/spring-boot.html#boot-features-spring-message-codes)）。
- 静态`index.html`支持。
- 自定义`Favicon`支持（[本文档稍后介绍](https://www.springcloud.cc/spring-boot.html#boot-features-spring-mvc-favicon)）。
- 自动使用`ConfigurableWebBindingInitializer` bean（本文 [后面会介绍](https://www.springcloud.cc/spring-boot.html#boot-features-spring-mvc-web-binding-initializer)）。

==如果你想保留Spring Boot MVC功能，并且你想添加额外的 [MVC配置](https://docs.spring.io/spring/docs/5.1.3.RELEASE/spring-framework-reference/web.html#mvc)（拦截器，格式化程序，视图控制器和其他功能），你可以添加自己的`@Configuration`类`WebMvcConfigurer`类但**没有** `@EnableWebMvc`。如果您希望提供`RequestMappingHandlerMapping`，`RequestMappingHandlerAdapter`或`ExceptionHandlerExceptionResolver`的自定义实例，则可以声明`WebMvcRegistrationsAdapter`实例以提供此类组件。==

==如果您想完全控制Spring MVC，可以添加自己的`@Configuration`注释`@EnableWebMvc`。==

$\textcolor{red}{步骤:}$ 

1. 在resources下新建一个文件夹i18n

2. 新建三个properties

   ![](D:\everything\springboot截图\国际化.png)

3. 在主配置文件中指定我们国际化资源的位置

   ```properties
   #配置自己的国际化文件位置
   spring.messages.basename=i18n.login
   ```

4. 新建一个类，该类实现LocaleResolver

   ```java
   public class MyLocaleResolver implements LocaleResolver {
       @Override
       public Locale resolveLocale(HttpServletRequest request) {
           // 使用分隔符获取请求中的语言加国家
           String l=request.getParameter("l");
           Locale locale=Locale.getDefault();
           if(!StringUtils.isEmpty(l)){
               String[] split=l.split("_");
               locale=new Locale(split[0],split[1]);
           }
           return locale;
       }

       @Override
       public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {

       }
   }
   ```

此时自己定义的localeResolver组件要添加到容器中

```java
@Configuration
public class MyConfig implements WebMvcConfigurer {

    /*
    这里是点击链接跳转 注意：要想springboot自行跳转的话，就需要把html文件放在static文件下，如果不是，就需要自定义跳转。
    */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login.html").setViewName("login");
       /*
       registry.addViewController("/").setViewName("login");
       相当于controller层
        @GetMapping(value = {"/login.html"})
        public String login() {
        return "login";
       }
        */
    }
// 加入容器
    @Bean
    public LocaleResolver localeResolver() {
        return new MyLocaleResolver();
    }
}
```
##### 认识json主流框架

三大主流框架:jackjson,gson,fastjson

序列化与反序列化：

序列化：对象-->json(响应json)

反序列化：json-->对象(请求参数是json)

HttpMessageConverter：转换器(json-->对象,对象-->json)，所有的Json工具都有自己的转换器(HttpMessageConverter)

