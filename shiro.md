#### shiro

###### 什么是shiro?

shiro是一个基于java的开源的安全管理框架，可以完成认证，授权，会话管理，加密，缓存的管理

Authentication认证：验证用户是否合法，也就是登录

Authorization授权：授与谁具有访问某些资源的权限

Session Management会话管理：用户登录后的用户信息通过Session Management来进行管理

Cryptography：加密，提高一些常见的加密算法，是的应用中方便实现数据安全

Web 支持：Shiro 的 Web 支持 API 有助于轻松保护 Web 应用程序。

Caching缓存：缓存是 Apache Shiro API 中的第一层公民，以确保安全操作保持快速和高效。

Concurrency并发：Apache Shiro 以其并发特性支持多线程应用程序。

测试：存在测试支持以帮助您编写单元和集成测试并确保您的代码按预期得到保护。

“Run As”：允许用户假设另一个用户的身份（如果允许）的功能，有时在管理场景中很有用。

“记住我”：跨会话记住用户的身份，这样他们只需要在强制登录时登录。

------

Subject：主体，可以是用户，也可以是第三方程序等，Subject用于获取主体信息，Principals和Credentials

Security Manager安全管理器：安全管理器是shiro架构的核心，由其来协调管理shiro各个组件直接的工作

Authenticator：认证器  负责验证用户的身份

Authorizer：授权器   负责为合法的用户指定其权限，控制用户可以反复问哪些资源

Reaims：域  用户通过shiro来完成相关的安全工作，shiro是不会去维护数据信息的。在shiro的工作过程中，数据的查询和获取工作是通过Reaim从不同的数据源获取的,Reaim可以获取数据库信息，文本信息等。在shiro中可以有一个reaim，也可以有多个

###### 用户认证: Authentication

验证用户的合法性，需要提交身份和凭证给shiro，Principals用户的身份信息，是Subject的标识属性，能够唯一识别Subject。如：电话，毫秒，电子邮箱，身份证号码等

Credentials凭证：密码 是只被subject知道的秘密值，可以是密码，也可以是数字证书

Principal/Credentials最常见的组合：用户名/密码。在shiro中通常使用UsernamePasswordToken来指定身份和凭证信息

###### 初始化项目:  认证 授权 加密

加入依赖

```xml
<!--shiro核心依赖包-->
    <dependencies>
        <dependency>
            <groupId>org.apache.shiro</groupId>
            <artifactId>shiro-core</artifactId>
            <version>1.9.0</version>
        </dependency>
        <!--日志依赖-->
        <dependency>
            <groupId>commons-logging</groupId>
            <artifactId>commons-logging</artifactId>
            <version>1.2</version>
        </dependency>
    </dependencies>
```

创建用户

```ini
[users]
user1=20011203
```

测试

```java
package text;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;

/**
 * @author Breeze
 * @address Shenzhen China
 * @email 3548297839@qq.com
 */
public class ShiroMain {
    public static void main(String[] args) {
        IniRealm factory = new IniRealm("classpath:shiro.ini");
        // 使用IniRealm从shiro.ini文件加载我们的用户和角色定义，然后使用它来配置DefaultSecurityManager对象
        SecurityManager securityManager = new DefaultSecurityManager(factory);
        SecurityUtils.setSecurityManager(securityManager);
        // 2.获取Subject对象
        Subject subject = SecurityUtils.getSubject();
        // 3.创建token对象，web应用用户名密码从页面传递
        UsernamePasswordToken token = new UsernamePasswordToken("user1", "20011203");
        // 完成登录
        try {
            subject.login(token);
            System.out.println("登录成功");
        } catch (UnknownAccountException e) {
            e.printStackTrace();
            System.out.println("用户名不存在");
        } catch (IncorrectCredentialsException e) {
            e.printStackTrace();
            System.out.println("密码错误");
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }
    }
}

```

判断角色:

```ini
[users]
user1=20011203,role1,role2

[roles]
role1=user:insert
```

```java
 subject.login(token);
 // 判断角色
 boolean role1 = subject.hasRole("role1");
 System.out.println("是否有role1角色:"+role1);
```

判断权限

```java
// 判断是否具有权限 第一种方式
            boolean permitted = subject.isPermitted("user:insert");
            System.out.println("是否有user:insert权限  "+permitted);
            // 第二种方式 如果不存在权限就抛出异常
            subject.checkPermission("user:insert1");
```

```java
Exception in thread "main" org.apache.shiro.authz.UnauthorizedException: Subject does not have permission [user:insert1]
	at org.apache.shiro.authz.ModularRealmAuthorizer.checkPermission(ModularRealmAuthorizer.java:323)
	at org.apache.shiro.mgt.AuthorizingSecurityManager.checkPermission(AuthorizingSecurityManager.java:137)
	at org.apache.shiro.subject.support.DelegatingSubject.checkPermission(DelegatingSubject.java:209)
	at text.ShiroMain.main(ShiroMain.java:35)

```

密码加密:

```java
 String password="a123456";
        // 使用md5加密
        Md5Hash passwd1=new Md5Hash(password);
        System.out.println(passwd1.toHex());
        System.out.println(passwd1.getBytes().length);
        // 将明文拼接为新字符串，然后加密
        Md5Hash passwd2=new Md5Hash(password,"salt");
        System.out.println("带盐的md5加密"+passwd2.toHex());
        System.out.println(passwd2.getBytes().length);
        // 多次加密
        Md5Hash passwd3=new Md5Hash(password,"salt",3);
        System.out.println("带盐的md5多次加密"+passwd3.toHex());
        System.out.println(passwd3.getBytes().length);
        // 使用父类加密 选择加密的方式
        SimpleHash simpleHash=new SimpleHash("MD5",password,"salt",3);
        System.out.println(simpleHash.toHex());
```

------

###### 自定义用户登录

1.继承AuthenticatingRealm

```java
public class MyRealm extends AuthenticatingRealm {
    // 自定义登录认证方法，shiro的login方法会调用该类的认证方法进行认证
    // 需要配置自定义的realm生效，在ini文件种配置，在springboot中配置
    // 改方法只是获取对进行的信息 认证逻辑按照底层shiro认证逻辑完成


    @Override // 认证
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 1.获取身份信息，获取凭证信息
        Object principal = authenticationToken.getPrincipal().toString();
        System.out.println("经过这里------");
        // 2.获取凭证信息
        String password=new String((char[]) authenticationToken.getCredentials());
        System.out.println("认证用户信息:"+principal+"----"+password);
        // 3.获取数据库中存储的信息
        if(principal.equals("user1")){
            // 3.1数据裤子存储的加盐三次迭代的密码
            String pwdInfo="723dcb176d70e525eb7631c1f97844e5";
            // 4.创建封装校验逻辑对象，封装数据返回
            AuthenticationInfo info = new SimpleAuthenticationInfo(authenticationToken.getPrincipal(), pwdInfo,"salt");
            return info;
        }
        return null;
    }
}
```

```ini
[main]
myrealm=text.MyRealm
securityManager.realms=$myrealm
```

