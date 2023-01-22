```java
package sprintText;

public class lifeCycle {
    public void init(){
        System.out.println("产生.....");
    }

    public void destroy(){
        System.out.println("销毁....");
    }

    public void ind(){
        System.out.println("进行中....");
    }

    public void last(){
        System.out.println("销毁.....");
    }
}
```

```java
package sprintText;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.Test;

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

// 一定是AbstractApplicationContext子接口创建的对象才会调用close()方法

     */
    @Test
    public void test2(){
        AbstractApplicationContext ac= new ClassPathXmlApplicationContext("scopeContext.xml");
        lifeCycle lifeCycle=ac.getBean("init", sprintText.lifeCycle.class);
        lifeCycle.destroy();
        lifeCycle.ind();
        ac.close();
        
        
        // 所谓延迟加载（lazy-init="true"）只有调用getBean（）[ac.getBean()]方法后，才会调用初始化方法init() 
        // 只有作用域为单列时，销毁方法才调用
        
        // 有这个（scope="prototype"）销毁方法，在关闭容器后并不会执行
    }
}
```

```xml
<bean id="sco" class="sprintText.scope" scope="prototype"/>
<bean id="init" class="sprintText.lifeCycle" init-method="init" destroy-method="last"/>
```

运行解果：

产生.....
销毁....
进行中....
销毁.....