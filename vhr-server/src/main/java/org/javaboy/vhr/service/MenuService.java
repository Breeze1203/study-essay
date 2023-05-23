package org.javaboy.vhr.service;


import jakarta.annotation.Resource;
import org.javaboy.vhr.bean.Hr;
import org.javaboy.vhr.bean.Menu;
import org.javaboy.vhr.mapper.MenuMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {

    @Resource(name = "MenuMapper")
    MenuMapper menuMapper;

    public List<Menu> getMenuByHrId(){
        // 从当前登录用户提取用户登录信息
        return menuMapper.
                getMenuByHrId((((Hr) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId()));
    }

    public List<Menu> getAllMenusWithRole(){
        return menuMapper.getAllMenusWithRole();
    }

    // 查询所有的菜单
    public List<Menu> getAllMenu(){
        return menuMapper.getAllMenu();
    }

    public List<Integer> getMenusByRole(Integer mid){
        return menuMapper.getMenusByRole(mid);
    }

}
