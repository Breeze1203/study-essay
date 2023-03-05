package org.javaboy.vhr.webconfig;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.javaboy.vhr.bean.Hr;
import org.javaboy.vhr.utils.StatusUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.io.PrintWriter;
import java.util.Arrays;


@Configuration
public class SecurityConfig {

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /*配置跨域问题*/
   @Bean
   CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:8080"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST","DELETE","PUT"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
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
                .loginProcessingUrl("/doLogin")
                .successHandler((request, response, authentication) -> {
                    response.setContentType("application/json;charset=utf-8");
                    Hr hr = (Hr) authentication.getPrincipal();
                    hr.setPassword(null);
                    PrintWriter out = response.getWriter();
                    out.write(new ObjectMapper().writeValueAsString(new StatusUtils("登录成功",200,hr)));
                    out.flush();
                    out.close();
                })
                .failureHandler((request, response, exception) -> {
                    response.setContentType("application/json;charset=utf-8");
                    PrintWriter out = response.getWriter();
                    StatusUtils statusUtils = new StatusUtils(500);
                    if(exception instanceof LockedException){
                        statusUtils.setMessage("登录失败，账号被锁定，请联系管理员");
                    } else if (exception instanceof CredentialsExpiredException) {
                        statusUtils.setMessage("登录失败，密码过期，请联系管理员");
                    }else if(exception instanceof AccountExpiredException){
                        statusUtils.setMessage("登录失败，账号过期，请联系管理员");
                    }else if(exception instanceof DisabledException){
                        statusUtils.setMessage("登录失败，账号被禁用，请联系管理员");
                    } else{
                        statusUtils.setMessage("账户名或密码输入错误,请重新输入");
                    }
                    out.write(new ObjectMapper().writeValueAsString(statusUtils));
                    out.flush();
                    out.close();
                })
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .clearAuthentication(true)
                .logoutSuccessHandler((request, response, authentication) -> {
                    response.setContentType("application/json;charset=utf-8");
                    PrintWriter out=response.getWriter();
                    StatusUtils statusUtils=new StatusUtils("注销成功",200);
                    out.print(new ObjectMapper().writeValueAsString(statusUtils));
                })
                .and()
                .cors()
                .and()
                .csrf()
                .disable();
                //.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
        // 这是允许将csrf令牌存储到cookie中

        return http.build();
    }

}
