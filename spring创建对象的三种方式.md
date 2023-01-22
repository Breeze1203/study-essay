```java
package sprintText;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Calendar;
import java.util.Date;

public class firstspring {
    public static void main(String[] args) {
        // 启动spring容器
        ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");

        // 使用无参构造器创建spring对象
        Student stu=ac.getBean("stu",Student.class);
        System.out.println(stu);
        Date date=ac.getBean("date",Date.class);
        System.out.println(date);

        //使用静态工厂创建spring对象
        Calendar cal=ac.getBean("cal",Calendar.class);
        System.out.println(cal);

        // 使用实列工厂创建spring对象
        Date datetime=ac.getBean("date",Date.class);
        System.out.println(datetime);
    }
}
```

```java
package sprintText;

public class Student {
    public Student() {
        System.out.println("student....");
    }
}
```

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
    <!--使用无参构造器创建spring对象-->
    <bean id="stu" class="sprintText.Student"></bean>
    <bean id="date" class="java.util.Date"></bean>
    <!--使用静态工厂创建spring对象-->
    <bean id="cal" class="java.util.Calendar" factory-method="getInstance"></bean>
    <!--使用实列工厂创建spring对象-->
    <bean id="time" factory-bean="cal" factory-method="getTime"></bean>
</beans>
```