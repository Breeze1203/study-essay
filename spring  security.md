## spring  security

##### 1.介绍

Spring Security 是一个专注于向 Java 应用程序提供身份验证和授权的安全框架，与所有 Spring 项目一样，Spring Security 的真正威力在于它可以很容易地扩展以满足定制需求。Spring Security 是采用 AOP 思想，基于 servlet 过滤器实现的，Spring Security 是 Spring Boot 底层安全模块默认的技术选型，可以实现强大的 web 安全控制，对于安全控制，仅需引入spring-boot-starter-security 模块，进行少量的配置，即可实现强大的安全管理

##### 2.新建项目

创建springboot项目，加入springsecurity依赖

```java
package com.example.springsecurity.Controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/hello")
    public String login(){
        return "spring security";
    }
}
```

当我们写一个Controller时，浏览器访问该controller路径，会自动跳转到一个登录界面

![](D:\everything\gitub\photo\springexecurity.png)

此登录界面验证通过才能访问我们自己写的controller

Username默认为user,

$\textcolor{red}{password为后台每次启动服务器生成的uuid}$

```java
        @Override
        public void configure(AuthenticationManagerBuilder auth) throws Exception {
            if (auth.isConfigured()) {
                return;
            }
            User user = this.securityProperties.getUser();
            if (user.isDefaultPassword()) {
                logger.info(String.format("%n%nUsing default security password: %s%n",
                        user.getPassword()));
            }
            Set<String> roles = new LinkedHashSet<>(user.getRole());
            withUser(user.getName()).password(user.getPassword())
                    .roles(roles.toArray(new String[roles.size()]));
            setField(auth, "defaultUserDetailsService", getUserDetailsService());
            super.configure(auth);
        }
```

![](D:\everything\gitub\photo\uuid.png)

```java
    public static class User {

        /**
         * Default user name.
         */
        private String name = "user";

        /**
         * Password for the default user name.
         */
        private String password = UUID.randomUUID().toString();

        /**
         * Granted roles for the default user name.
         */
        private List<String> role = new ArrayList<>(Collections.singletonList("USER"));

        private boolean defaultPassword = true;

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPassword() {
            return this.password;
        }

        public void setPassword(String password) {
            if (password.startsWith("${") && password.endsWith("}")
                    || !StringUtils.hasLength(password)) {
                return;
            }
            this.defaultPassword = false;
            this.password = password;
        }

        public List<String> getRole() {
            return this.role;
        }

        public void setRole(List<String> role) {
            this.role = new ArrayList<>(role);
        }

        public boolean isDefaultPassword() {
            return this.defaultPassword;
        }

    }
```

验证通过后，会跳到http://localhost:8080/，若没有controller指定该路径就会报错

![](D:\everything\gitub\photo\error.png)

将路径改为我们自己的controller就会跳转过来

![](D:\everything\gitub\photo\hello.png)

######  （1）在配置文件里面配置用户名和密码

每次Password生成的uuid都不同，我们可以自己application.properties定义

```properties
spring.security.user.password=123
spring.security.user.name=root
```

###### （2）在类里面配置

```java
package com.example.springsecurity;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
public class WebConfigSecurity extends WebSecurityConfigurerAdapter {

    @Bean
    PasswordEncoder passwordEncoder(){
        /* 返回一个不进行加加密的实例 */
        return NoOpPasswordEncoder.getInstance();
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("javaboy").password("13123");
    }
}

```

###### (3),自定义表单登录页:

```java
@Override
    public void configure(WebSecurity web) throws Exception {
        /*  不拦截静态资源  */
        web.ignoring().antMatchers("/js/**","/css/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login.html")
                .permitAll()
                .and()
                .csrf()
                .disable();
    }
```

