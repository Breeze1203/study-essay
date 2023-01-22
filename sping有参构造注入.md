```java
package ioc2;

public class A {
    //  构造器注入
    private B b;
    private C c;

    public A(B b,C c) {
        this.b = b;
        this.c=c;
        System.out.println("经过这里....");
    }

    public A() {
    }
    /*
        利用接口，使类继承接口
        然后后早期就给接口
        private bcInterface b;
        public A(bcInterface b) {
            this.b = b;
        }
        这里就只需要更改ref属性值
        <bean id="b1" class="ioc2.B"/>
        <bean id="c1" class="ioc2.C"/>
        <bean id="a1" class="ioc2.A">
            <constructor-arg index="0" ref="b1"/>
        </bean>
         */

    public void execute(){
        b.f();
        System.out.println("execte()");
    }
}
```

```java
package ioc2;

public class B{
    // 无参构造器
    public B() {
        System.out.println("B()");
    }
    public void f(){
        System.out.println("B.s fi()");
    }

}
```

```java
package ioc2;

public class C{
    public C() {
        System.out.println("C()");
    }

    public void f(){
        System.out.println("C's fc()");
    }
}
```

```java
package ioc2;

public interface bcInterface {
    public void f();
}
```

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
    <!-- 控制反转，ioc,对象的依赖注入
    方法一：
    <bean id="a" class="ioc.A">
        <property name="b" ref="c"/>
    </bean>
    <bean id="b" class="ioc.B"/>
    <bean id="c" class="ioc.C"/>
    -->

    <!--
    constructor-arg元素：
    用来配置构造器方式的注入，其中index属性指定参数的下标（从零开始）
    -->
    <bean id="b1" class="ioc2.B"/>
    <bean id="c1" class="ioc2.C"/>
    <bean id="a1" class="ioc2.A">
        <constructor-arg index="0" ref="b1"/>
        <constructor-arg index="1" ref="c1"/>
    </bean>
</beans>
```

```java
package ioc2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.Test;

public class test {
    @Test
    public void test1(){
        ApplicationContext ac=new ClassPathXmlApplicationContext("abContext.xml");
        A a1=ac.getBean("a1",A.class);
        a1.execute();
    }
}
```

