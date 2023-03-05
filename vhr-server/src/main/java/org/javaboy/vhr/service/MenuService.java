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
        return menuMapper.
                getMenuByHrId((((Hr) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId()));
    }
}
