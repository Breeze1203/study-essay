```java
package com.spring.Admin;

public class Admin {
    private String adminCode;
    private String password;

    private String name;

    @Override
    public String toString() {
        return "Admin{" +
                "adminCode='" + adminCode + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public String getAdminCode() {
        return adminCode;
    }

    public void setAdminCode(String adminCode) {
        this.adminCode = adminCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private int age;




    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
```

```java
package com.spring.Admin;

/*
* 持久层接口
* */
public interface AdminDao {
    public Admin findByAdmincode(String admincode);
}
```

```java
package com.spring.Admin;

import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository("adminDao")
public class AdminDaoImpl implements AdminDao{
    // 采用注解依赖注入连接池
    @Resource(name = "da")
    private DataSource da;
    @Override
    public Admin findByAdmincode(String admincode) {
        Admin admin=null;
        Connection con=null;
        try {
            con= da.getConnection();
            String sql="select * from admin where adminCode=?";
            PreparedStatement statement= con.prepareStatement(sql);
            statement.setString(1,admincode);
            ResultSet resultSet=statement.executeQuery();
            if(resultSet.next()){
                admin=new Admin();
                admin.setAdminCode(resultSet.getString("adminCode"));
                admin.setPassword(resultSet.getString("password"));
                admin.setAge(resultSet.getInt("age"));
                admin.setName(resultSet.getString("name"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return admin;
    }
}
```

```java
package com.spring.Service;


import com.spring.Admin.Admin;

public interface Loginservice {
    /*
    业务层接口
     */
    public Admin CheckCode(String adminCode, String password);
}
```

```java
package com.spring.Service;

import com.spring.Admin.Admin;
import com.spring.Admin.AdminDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/*
业务层实现
 */
@Service(value = "loginService")
public class LoginServicImpl implements Loginservice{
    @Resource(name = "adminDao")
    private AdminDao dao;

    @Override
    public Admin CheckCode(String adminCode, String password) {
        Admin admin=null;
        admin=dao.findByAdmincode(adminCode);
        if(admin==null){
            // 账户不存在,抛出一个应用异常(用户操作引起的异常)
            throw new ApplicationException("账户不存在");
        }
        if(!admin.getPassword().equals(password)){
            throw new ApplicationException("密码错误");
        }
        // 登录成功 返回对象
        return admin;
    }
}
```

```java
package com.spring.Controller;
/*
展示层
*/
import com.spring.Admin.Admin;
import com.spring.Service.ApplicationException;
import com.spring.Service.Loginservice;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
public class Logincontroller{
    @Resource(name = "loginService")
    private Loginservice loginservice;

    @RequestMapping("login.do")
    public String login(HttpServletRequest request) {
            String adminCode = request.getParameter("adminCode");
            String password = request.getParameter("password");
            // System.out.println(adminCode + "   " + password);
            try {
                Admin admin = loginservice.CheckCode(adminCode, password);
            } catch (Exception e) {
                e.printStackTrace();
                if (e instanceof ApplicationException) {
                    request.setAttribute("login_failed", e.getMessage());
                    return "login";
                }
                // 处理系统异常
                // return "error"
            }
            return "redirect:init";
        }


}
```

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:util="http://www.springframework.org/schema/util"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:mvc="http://www.springframework.org/schema/cache"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/util https://www.springframework.org/schema/util/spring-util.xsd http://www.springframework.org/schema/context https://www.springframework.org/schema/context/spring-context.xsd http://www.springframework.org/schema/cache http://www.springframework.org/schema/cache/spring-cache.xsd">


    <util:properties id="config" location="classpath:db.properties"/>

    <context:component-scan base-package="com.spring"/>

    <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
        <property name="prefix" value="/jsp/"/>
        <property name="suffix" value=".jsp"/>
    </bean>


    <!--配置连接池-->
    <bean id="da" class="org.apache.commons.dbcp.BasicDataSource" destroy-method="close">
        <property name="driverClassName" value="#{config.driverclass}"/>
        <property name="url" value="#{config.url}"/>
        <property name="username" value="#{config.user}"/>
        <property name="password" value="#{config.password}"/>
    </bean>

</beans>
```

```xml
driverclass=com.mysql.cj.jdbc.Driver
url=jdbc:mysql://localhost:3306/text
user=root
password=3548297839
Size=2
MaxActive=2

<!--连接池配置文件-->
```