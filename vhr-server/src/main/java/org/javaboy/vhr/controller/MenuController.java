package org.javaboy.vhr.controller;

import org.javaboy.vhr.bean.Menu;
import org.javaboy.vhr.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Breeze
 * @address Shenzhen China
 * @email 3548297839@qq.com
 */
@RestController
@RequestMapping("/api/menus")
public class MenuController {
    @Autowired
    MenuService menuService;

    @GetMapping("/getAllMenus")
    public List<Menu> getAllMenus() {
        return menuService.getAllMenu();
    }

    @PostMapping("/getMenusByRole")
    public List<Integer> getMenusByRole(@RequestParam("mid") Integer mid) {
        return menuService.getMenusByRole(mid);
    }
}
