注入基本类型的值，使用value属性即可；

注入集合类型的值：

```java
package value;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class valueBeen {
    private String name;
    private int age;

    private List city;

    private Set interest;

    private Map scrose;

    private Properties db;

    public void setDb(Properties db) {
        this.db = db;
    }

    public void setScrose(Map scrose) {
        this.scrose = scrose;
    }

    @Override
    public String toString() {
        return "valueBeen{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", city=" + city +
                ", interest=" + interest +
                ", scrose=" + scrose +
                ", db=" + db +
                '}';
    }


    public void setInterest(Set interest) {
        this.interest = interest;
    }

    public void setCity(List city) {
        this.city = city;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }


    public valueBeen() {
        System.out.println("注入基本类型的值....");
    }
}
```

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
    <bean id="va" class="value.valueBeen">
        <property name="name" value="张三"/>
        <property name="age" value="19"/>
        <property name="city">
            <list>
                <value>北京</value>
                <value>上海</value>
                <value>深圳</value>
            </list>
        </property>
        <property name="interest">
            <set>
                <value>听歌</value>
                <value>跑步</value>
                <value>钓鱼</value>
            </set>
        </property>
        <property name="scrose">
            <map>
                <entry key="语文" value="90"/>
                <entry key="英语" value="90"/>
                <entry key="数学" value="98"/>
            </map>
        </property>
        <property name="db">
            <props>
                <prop key="username">王五</prop>
                <prop key="password">12423r34</prop>
            </props>
        </property>
    </bean>
</beans>
```

```java
package value;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.Test;

public class text {
    @Test
    public void test1(){
        ApplicationContext ac=new ClassPathXmlApplicationContext("value.xml");
        valueBeen val=ac.getBean("va",valueBeen.class);
        System.out.println(val);
    }
}
```
```xml
<util:list id="cityBean">
    <value>上海</value>
    <value>北京</value>
    <value>哈尔滨</value>
</util:list>
<util:map id="map">
    <entry key="语文" value="90"/>
    <entry key="英语" value="90"/>
    <entry key="数学" value="98"/>
</util:map>
<util:properties id="properties">
    <prop key="username">王五</prop>
    <prop key="password">12423r34</prop>
</util:properties>
<util:set id="interest">
    <set>
        <value>听歌</value>
        <value>跑步</value>
        <value>钓鱼</value>
    </set>
</util:set>
<bean id="vb" class="value.valueBeen">
    <property name="interest" ref="interest"/>
    <property name="city" ref="cityBean"/>
    <property name="age" value="27"/>
    <property name="name" value="赵文"/>
    <property name="scrose" ref="map"/>
    <property name="db" ref="properties"/>
</bean>
<util:properties id="config" location="classpath:text.properties"/>  // 通过spring读取配置文件
```

```java
public void test2(){
    ApplicationContext ac=new ClassPathXmlApplicationContext("va.xml");
    valueBeen val=ac.getBean("vb",valueBeen.class);
    System.out.println(val);
}
```

