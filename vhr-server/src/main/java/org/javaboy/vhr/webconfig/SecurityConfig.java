package org.javaboy.vhr.webconfig;


import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.javaboy.vhr.bean.Hr;
import org.javaboy.vhr.utils.StatusUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.*;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;


import java.io.IOException;
import java.io.PrintWriter;


@Configuration
public class SecurityConfig {

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    DefaultSecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.httpBasic()
                .and()
                .authorizeHttpRequests()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .usernameParameter("username")
                .passwordParameter("password")
                .loginProcessingUrl("/api/doLogin")
                .successHandler((request, response, authentication) -> {
                    response.setContentType("application/json;charset=utf-8");
                    Hr hr = (Hr) authentication.getPrincipal();
                    hr.setPassword(null);
                    PrintWriter out = response.getWriter();
                    out.write(new ObjectMapper().writeValueAsString(new StatusUtils("登录成功", 200, hr)));
                    out.flush();
                    out.close();
                })
                .failureHandler((request, response, exception) -> {
                    response.setContentType("application/json;charset=utf-8");
                    PrintWriter out = response.getWriter();
                    StatusUtils statusUtils = new StatusUtils(500);
                    if (exception instanceof LockedException) {
                        statusUtils.setMessage("登录失败，账号被锁定，请联系管理员");
                    } else if (exception instanceof CredentialsExpiredException) {
                        statusUtils.setMessage("登录失败，密码过期，请联系管理员");
                    } else if (exception instanceof AccountExpiredException) {
                        statusUtils.setMessage("登录失败，账号过期，请联系管理员");
                    } else if (exception instanceof DisabledException) {
                        statusUtils.setMessage("登录失败，账号被禁用，请联系管理员");
                    } else {
                        statusUtils.setMessage("账户名或密码输入错误,请重新输入");
                    }
                    out.write(new ObjectMapper().writeValueAsString(statusUtils));
                    out.flush();
                    out.close();
                })
                //.permitAll()
                .and()
                .logout()
                .logoutUrl("/api/logout")
                .clearAuthentication(true)
                .logoutSuccessHandler((request, response, authentication) -> {
                    response.setContentType("application/json;charset=utf-8");
                    PrintWriter out = response.getWriter();
                    StatusUtils statusUtils = new StatusUtils("注销成功", 200);
                    out.print(new ObjectMapper().writeValueAsString(statusUtils));
                })
                .and()
                .csrf()
                .disable()
                // 认证失败的处理
                .exceptionHandling().authenticationEntryPoint(new AuthenticationEntryPoint() {
                    @Override
                    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
                        response.setContentType("application/json;charset=utf-8");
                        PrintWriter out=response.getWriter();
                        if(authException instanceof InsufficientAuthenticationException){
                            // 将java对象转为字符串
                            out.print(new ObjectMapper().writeValueAsString(new StatusUtils(401)));
                        }
                    }
                });
        //.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
        // 这是允许将csrf令牌存储到cookie中

        return http.build();
    }

}
