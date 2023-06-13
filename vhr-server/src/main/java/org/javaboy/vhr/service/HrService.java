package org.javaboy.vhr.service;

import jakarta.annotation.Resource;
import org.javaboy.vhr.bean.Hr;
import org.javaboy.vhr.mapper.HrMapper;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/*实现UserDetailsService接口实现登录验证*/

@Configuration
public class HrService implements UserDetailsService {
    @Resource(name = "Hr")
    HrMapper hrMapper;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Hr hr = hrMapper.findHrByUsername(username);
        // 用户不存在
        if (hr == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        System.out.println(hr);
        hr.setRoles(hrMapper.getHrRolesById(hr.getId()));
        return hr;
    }


}
