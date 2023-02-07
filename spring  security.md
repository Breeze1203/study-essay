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

(1).在配置文件里面配置用户名和密码

每次Password生成的uuid都不同，我们可以自己application.properties定义

```properties
spring.security.user.password=123
spring.security.user.name=root
```

###### (2).在类里面配置

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

###### (3).自定义登录页

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

######  (4).登录接口

登录接口则是提交登录数据的地方，就是登录页面里边的 form 表单的 action 属性对应的值。

在 Spring Security 中，如果我们不做任何配置，默认的登录页面和登录接口的地址都是 `/login`，也就是说，默认会存在如下两个请求：

- GET http://localhost:8080/login
- POST http://localhost:8080/login

在 SecurityConfig 中，我们可以通过 loginProcessingUrl 方法来指定登录接口地址，如下

```java
@Override
protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests().anyRequest().authenticated()
            .and()
            .formLogin()
            .loginPage("/login.html")
            .loginProcessingUrl("/login") // 登录接口
            .usernameParameter("user")  // 定义登录界面输入框
            .passwordParameter("password")
  //          .successForwardUrl("/")  不管从哪过来登录成功就跳转到s界面 服务端跳转
            .defaultSuccessUrl("/s") // 重定向 如果不是登录界面过来的，登录成功会跳到刚开始那个页面
            .permitAll()
            .and()
            .csrf()
            .disable();
}
```

###### (5).登录回调

在 Spring Security 中，和登录成功重定向 URL 相关的方法有两个：

- defaultSuccessUrl
- successForwardUrl

首先我们在配置的时候，defaultSuccessUrl 和 successForwardUrl 只需要配置一个即可，具体配置哪个，则要看你的需求，两个的区别如下：

1. defaultSuccessUrl 有一个重载的方法，我们先说一个参数的 defaultSuccessUrl 方法。如果我们在 defaultSuccessUrl 中指定登录成功的跳转页面为 `/index`，此时分两种情况，如果你是直接在浏览器中输入的登录地址，登录成功后，就直接跳转到 `/index`，如果你是在浏览器中输入了其他地址，例如 `http://localhost:8080/hello`，结果因为没有登录，又重定向到登录页面，此时登录成功后，就不会来到 `/index` ，而是来到 `/hello` 页面。
2. defaultSuccessUrl 还有一个重载的方法，第二个参数如果不设置默认为 false，也就是我们上面的的情况，如果手动设置第二个参数为 true，则 defaultSuccessUrl 的效果和 successForwardUrl 一致。
3. successForwardUrl 表示不管你是从哪里来的，登录后一律跳转到 successForwardUrl 指定的地址。例如 successForwardUrl 指定的地址为 `/index` ，你在浏览器地址栏输入 `http://localhost:8080/hello`，结果因为没有登录，重定向到登录页面，当你登录成功之后，就会服务端跳转到 `/index` 页面；或者你直接就在浏览器输入了登录页面地址，登录成功后也是来到 `/index`

```java
.and()
.formLogin()
.loginPage("/login.html")
.loginProcessingUrl("/doLogin")
.usernameParameter("name")
.passwordParameter("passwd")
.defaultSuccessUrl("/index")
.successForwardUrl("/index")
.permitAll()
.and()
```

######  (6).登录失败回调

注销登录的默认接口是 `/logout`，我们也可以自己配置

```java
.and()
.logout()
.logoutUrl("/logout")
.logoutRequestMatcher(new AntPathRequestMatcher("/logout","POST"))
.logoutSuccessUrl("/index")
.deleteCookies()
.clearAuthentication(true)
.invalidateHttpSession(true)
.permitAll()
.and()
```

1. 默认注销的 URL 是 `/logout`，是一个 GET 请求，我们可以通过 logoutUrl 方法来修改默认的注销 URL。
2. logoutRequestMatcher 方法不仅可以修改注销 URL，还可以修改请求方式，实际项目中，这个方法和 logoutUrl 任意设置一个即可。
3. logoutSuccessUrl 表示注销成功后要跳转的页面。
4. deleteCookies 用来清除 cookie。
5. clearAuthentication 和 invalidateHttpSession 分别表示清除认证信息和使 HttpSession 失效，默认可以不用配置，默认就会清除

###### (7).前后端分类的数据交换

在前后端分离这样的开发架构下，前后端的交互都是通过 JSON 来进行，无论登录成功还是失败，都不会有什么服务端跳转或者客户端跳转之类。

登录成功了，服务端就返回一段登录成功的提示 JSON 给前端，前端收到之后，该跳转该展示，由前端自己决定，就和后端没有关系了。

登录失败了，服务端就返回一段登录失败的提示 JSON 给前端，前端收到之后，该跳转该展示，由前端自己决定，也和后端没有关系了

###### (8).登录成功

之前我们配置登录成功的处理是通过如下两个方法来配置的：

- defaultSuccessUrl
- successForwardUrl

这两个都是配置跳转地址的，适用于前后端不分的开发。除了这两个方法之外，还有一个必杀技，那就是 successHandler。

$\textcolor{red}{successHandler 的功能十分强大，甚至已经囊括了 defaultSuccessUrl 和 successForwardUrl 的功能}$

```java
.successHandler((req, resp, authentication) -> {

    Object principal = authentication.getPrincipal();

    resp.setContentType("application/json;charset=utf-8");

    PrintWriter out = resp.getWriter();

    out.write(new ObjectMapper().writeValueAsString(principal));

    out.flush();

    out.close();

})

```

successHandler 方法的参数是一个 AuthenticationSuccessHandler 对象，这个对象中我们要实现的方法是 onAuthenticationSuccess。

onAuthenticationSuccess 方法有三个参数，分别是：

- HttpServletRequest
- HttpServletResponse
- Authentication

有了前两个参数，我们就可以在这里随心所欲的返回数据了。利用 HttpServletRequest 我们可以做服务端跳转，利用 HttpServletResponse 我们可以做客户端跳转，当然，也可以返回 JSON 数据。

$\textcolor{red}{第三个 Authentication 参数则保存了我们刚刚登录成功的用户信息}$

######  (9).登录失败

登录失败也有一个类似的回调，如下

```java
.failureHandler((req, resp, e) -> {
    resp.setContentType("application/json;charset=utf-8");
    PrintWriter out = resp.getWriter();
    out.write(e.getMessage());
    out.flush();
    out.close();
})
```

失败的回调也是三个参数，前两个就不用说了，第三个是一个 Exception，对于登录失败，会有不同的原因，Exception 中则保存了登录失败的原因，我们可以将之通过 JSON 返回到前端

###### (10).未认证处理方案

前后端分离中，这个逻辑明显是有问题的，如果用户没有登录就访问一个需要认证后才能访问的页面，这个时候，我们不应该让用户重定向到登录页面，而是给用户一个尚未登录的提示，前端收到提示之后，再自行决定页面跳转。

要解决这个问题，就涉及到 Spring Security 中的一个接口 `AuthenticationEntryPoint` ，该接口有一个实现类：`LoginUrlAuthenticationEntryPoint` ，该类中有一个方法 `commence`，如下

```java
.csrf().disable().exceptionHandling()
.authenticationEntryPoint((req, resp, authException) -> {
            resp.setContentType("application/json;charset=utf-8");
            PrintWriter out = resp.getWriter();
            out.write("尚未登录，请先登录");
            out.flush();
            out.close();
        }
);
```

###### (11).注销登录

注销登录之后，系统自动跳转到登录页面，这也是不合适的，如果是前后端分离项目，注销登录成功后返回 JSON 即可，配置如下：

```java
.and()
.logout()
.logoutUrl("/logout")
.logoutSuccessHandler((req, resp, authentication) -> {
    resp.setContentType("application/json;charset=utf-8");
    PrintWriter out = resp.getWriter();
    out.write("注销成功");
    out.flush();
    out.close();
})
.permitAll()
.and()
```

