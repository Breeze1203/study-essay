package org.javaboy.vhr.controller;


import org.javaboy.vhr.bean.Menu;
import org.javaboy.vhr.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class SystemConfigController {

    @Autowired
    MenuService menuService;

    @PostMapping("/menu")
    public List<Menu> allMenu(){
        System.out.println("/system/menu");
        return menuService.getMenuByHrId();
    }
}
