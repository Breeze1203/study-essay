```java
package com.spring.Springmybatis;

public class students {
    private int id;
    private String name;
    private int age;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "students{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public void setAge(int age) {
        this.age = age;
    }
}
```

#### Mapper映射接口

```java
package com.spring.mapper;

import com.spring.Springmybatis.students;

import java.util.List;
import java.util.Map;

public interface studentsMapper {
    /*
    * Mybatis面向接口编程的两个一致
    * 1.映射文件的namespace要和mapper接口的全类名保持一致
    * 2.映射文件中的sql语句的id要和mapper接口中方法名一致
    * */
    public void insertStudent(students s);
    public List<students> findAll();

    public students findbyId(int id);

    public int deleteById(int id);

    public Map findById2(int id);

}
```

#### 测试类

```java
package com.spring.Springmybatis;

import com.spring.mapper.studentsMapper;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Text {
    @Test
    public void text(){
        AbstractApplicationContext ac=new ClassPathXmlApplicationContext("spring-mvc.xml");
        // 映射接口首字母小写为缺省值id
        // 也可以在接口类前面加上@Repository("")
        studentsMapper stu=ac.getBean("studentsMapper",studentsMapper.class);
        students students=new students();
        students.setId(3);
        students.setAge(20);
        students.setName("彭宇");
        stu.insertStudent(students);
        System.out.println(students);
    }
}
```

```xml
<!--mapper映射文件-->

<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper  PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.spring.mapper.studentsMapper">
    <!--插入-->
    <insert id="insertStudent" parameterType="com.spring.Springmybatis.students">
        insert into student_2(id, name, age)
        values (#{id}, #{name}, #{age})
    </insert>
    <!--查询所有-->
    <select id="findAll" resultType="com.spring.Springmybatis.students">
        select *
        from student_2
    </select>
    <!--根据Id查找-->
    <select id="findbyId" resultType="com.spring.Springmybatis.students" parameterType="int">
        select name
        from student_2
        where id = #{id}
    </select>
    <!--删除-->
    <delete id="deleteById" parameterType="int">
        delete
        from student_2
        where id = #{id}
    </delete>
    <!--map形式-->
    <select id="findById2" parameterType="int" resultType="map">
        select name
        from student_2
        where id = #{id}
    </select>
</mapper>
```

#### Spring配置文件

```xml
<!--spring整合mybatis-->
<bean class="org.mybatis.spring.SqlSessionFactoryBean">
    <!--注入连接池-->
    <property name="dataSource" ref="da"/>
    <!--指定映射文件-->
    <property name="mapperLocations" value="mappers/spring-mybatis.xml"/>
</bean>

<!--配置MapperScannerConfigurer

-->
<bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
    <property name="basePackage" value="com.spring.mapper"/>
</bean>
```

