package org.javaboy.vhr.controller;


import org.javaboy.vhr.bean.Hr;
import org.javaboy.vhr.bean.Menu;
import org.javaboy.vhr.mapper.HrMapper;
import org.javaboy.vhr.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SystemConfigController {

    @Autowired
    HrMapper hrMapper;
    @Autowired
    MenuService menuService;

    @PostMapping("/api/menu")
    public List<Menu> allMenu(){
        System.out.println(menuService.getMenuByHrId());
        return menuService.getMenuByHrId();
    }

    @PostMapping("/api/insert")
    public void insert(){
        Hr hr=new Hr();
        hr.setPassword("$2a$10$oE39aG10kB/rFu2vQeCJTu/V/v4n6DRR0f8WyXRiAYvBpmadoOBE.");
        hr.setId(13);
        hr.setName("张三");
        hr.setAddress("wqeqwerq");
        hr.setPhone("13213214");
        hr.setTelephone("121324234");
        hr.setUsername("章程");
        hrMapper.insertHr(hr);
    }
}
