```java
package ioc2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class restaurant {
    public void setWt(writer wt) {
        this.wt = wt;
    }

    private writer wt;

    public restaurant() {
        System.out.println("restaurant------");
    }

    public void res() {
        wt.wri();
        System.out.println("restaurant res....");
    }

    public static void main(String[] args) {
        ApplicationContext ac=new ClassPathXmlApplicationContext("wr.xml");
        restaurant a=ac.getBean("rs",restaurant.class);
        a.res();
    }
}
```

```java
package ioc2;

public class writer {
    public writer() {
        System.out.println("writer.....");
    }

    public void wri(){
        System.out.println("class writer....");
    }
}
```

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
    <!-- 利用outowire的byname属性，执行过程是会去寻找restaurant
    类里的writer的属性名(private writer wt这里的wt)，然后通过调用set[setwt()]方法
    <bean id="wt" class="ioc2.writer"/>
    <bean id="rs" class="ioc2.restaurant" autowire="byName"/>
    -->

    <!--
    利用outowire的byTyoe属性，执行过程是会去寻找restaurant
    类里的writer的属性类型(private writer wt这里的writer属性)，然后通过调用set[setwt()]方法
    -->
    <bean id="wt" class="ioc2.writer"/>
    <bean id="rs" class="ioc2.restaurant" autowire="byType"/>


    <!--与bytype类似,不同的是调用对应的构造器注入

    <bean id="wt" class="ioc2.writer"/>
    <bean id="rs" class="ioc2.restaurant" autowire="constructor"/>
    -->
</beans>
```