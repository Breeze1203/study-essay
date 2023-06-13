package org.javaboy.vhr.utils;

import org.javaboy.vhr.bean.Hr;
import org.javaboy.vhr.webconfig.SecurityConfig;
import org.springframework.security.config.annotation.web.configurers.SecurityContextConfigurer;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * 获取当前登录用户的id
 *
 * @author breeze
 * @date 2023/6/3
 * @Email 3548297839@qq.com
 * @position Shenzhen China
 */
public class HrUtil {
    // 获取当前登录用户信息
    public static Hr getCurrentHr(){
        return (Hr) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
