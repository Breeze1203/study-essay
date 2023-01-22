```java
package annotation;


import org.springframework.stereotype.Component;

import javax.annotation.Resource;


@Component("bar")
public class Bar {
    private Writer wt;

    public Bar() {
        System.out.println("wt......");
    }

    @Resource(name="wt")
    public void setWt(Writer wt) {
        System.out.println("Wt....");
        this.wt = wt;
    }

    @Override
    public String toString() {
        return "Bar{" +
                "wt=" + wt +
                '}';
    }
}
```

```java
package annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("rest")
public class Restaurant {
    public Writer wt;

    @Autowired
    public void setWt(@Qualifier(value = "wt") Writer wt) {
        System.out.println("wt.....");
        this.wt = wt;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "wt=" + wt +
                '}';
    }
}
```

```java
package annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("school")
public class School {
    private Writer wt;

    @Autowired
    public School(@Qualifier("wt") Writer wt) {
        System.out.println("Writer wt.....");
        this.wt = wt;
    }

    public School() {
        System.out.println("school...");
    }

    @Override
    public String toString() {
        return "School{" +
                "wt=" + wt +
                '}';
    }
}
```

```java
package annotation;

import org.springframework.stereotype.Component;

@Component("wt")
public class Writer {
    public Writer() {
        System.out.println("Writer.....");
    }
}
```

```java
package annotation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("pg")
public class Pagesize {
    @Value("彭浩")
    private String name;
    @Value("#{config.pagesize}")
    private String Pagesize;

    public Pagesize() {
        System.out.println("pageSize....");
    }

    @Override
    public String toString() {
        return "Pagesize{" +
                "name='" + name + '\'' +
                ", Pagesize='" + Pagesize + '\'' +
                '}';
    }
}
```

```java
package annotation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.Test;


public class Wrtest {
    // 测试a@Autowired(set方式注入)
    @Test
    public void test1() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("wrt.xml");
        Restaurant re = ac.getBean("rest", Restaurant.class);
        System.out.println(re);
    }

    // 测试a@Autowired(构造器方式注入)
    @Test
    public void test2() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("wrt.xml");
        School re = ac.getBean("school", School.class);
        System.out.println(re);
    }
    // 测试@Resource
    @Test
    public void test3(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("wrt.xml");
        Bar re=ac.getBean("bar",Bar.class);
        System.out.println(re);
    }
    // 测试@Value
    @Test
    public void test4(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("wrt.xml");
        Pagesize pg=ac.getBean("pg",Pagesize.class);
        System.out.println(pg);
    }
}
```

