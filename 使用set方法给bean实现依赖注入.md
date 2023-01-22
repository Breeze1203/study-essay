```java
package ioc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.Test;
import sprintText.lifeCycle;

public class scopeText {
    // @Test
    // 生命周期测试
    /*
    public void text1() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("scopeContext.xml");
        scope test1 = ac.getBean("sco", scope.class);
        scope test2 = ac.getBean("sco", scope.class);
        System.out.println(test1);
        System.out.println(test2);
        System.out.println(test1 == test2);
    }


     */
    @Test
    public void test2() {
        AbstractApplicationContext ac = new ClassPathXmlApplicationContext("scopeContext.xml");
        lifeCycle lifeCycle = ac.getBean("init", lifeCycle.class);
        lifeCycle.destroy();
        lifeCycle.ind();
        ac.close();
    }

    /* 先调用两个类的构造方法，<bean id="a" class="sprintText.A">
                               <property name="b" ref="b"/>
                          </bean>
                          <bean id="b" class="sprintText.B"/>
    经过property属性值然后调用b.setB(),然后调用a.textA();
    输出结果为：
    A....
    B,进行这一步中
    正在进行中....
    测试B....
    B....

     */
    
    
    // 依赖注入
    @Test
    public void test3() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("abContext.xml");
        A a = ac.getBean("a", A.class);
        a.textA();
    }
    /*
    此方法测试：创建三个类ABC,B和C对A有依赖关系(不修改类源代码的情况，修改配置文件即可)
    思路：创建一个接口，BC类都实现，接口里写方法，bc类里重写
    然后类A中，定义 private bcInterface b;
    public void setB(bcInterface b) {
        System.out.println("正在进行中....");
        this.b=b;
    }
    <bean id="a" class="ioc.A">
        <property name="b" ref="c"/> 
        ref属性接的是被注入bean的id(同理：要跟c注入关系的话，属性值改为b的id)
    </bean>
    <bean id="b" class="ioc.B"/>
    <bean id="c" class="ioc.C"/>
    */
    @Test
    public void test4(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("abContext.xml");
        A c=ac.getBean("a",A.class);
        c.textA();
    }
}
```

```java
package ioc;

public class A {
    private bcInterface b;

    public void setB(bcInterface b) {
        System.out.println("正在进行中....");
        this.b=b;
    }

    public A() {
        System.out.println("A....");
    }

    public void textA(){
        b.f();
        System.out.println("B....");
    }
}
```

```java
package ioc;

public class B implements bcInterface{

    public B() {
        System.out.println("A....");
    }


    @Override
    public void f() {
        System.out.println("B在进行中....");
    }
}
```

```java
package ioc;

public class C implements bcInterface{
    public C() {
        System.out.println("输出C.....");
    }

    public void f(){
        System.out.println("C在进行中");
    }
}
```

