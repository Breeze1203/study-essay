package org.javaboy.vhr.controller;

import jakarta.annotation.Resource;
import org.javaboy.vhr.bean.Menu;
import org.javaboy.vhr.bean.Role;
import org.javaboy.vhr.service.MenuService;
import org.javaboy.vhr.service.RoleService;
import org.javaboy.vhr.utils.StatusUtils;
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

    @Resource(name = "RoleService")
    RoleService roleService;

    @GetMapping("/getAllMenus")
    public List<Menu> getAllMenus() {
        return menuService.getAllMenu();
    }

    @PostMapping("/getMenusByRole")
    public List<Integer> getMenusByRole(@RequestParam("mid") Integer mid) {
        return menuService.getMenusByRole(mid);
    }

    @PostMapping("/updateMenusByRole")
    public StatusUtils updateMenusByRole(@RequestParam("mids") Integer[] mids, @RequestParam("rid") Integer rid) {
        StatusUtils statusUtils = new StatusUtils();
        int intResult = menuService.insertRoleMenu(mids, rid);
        if (intResult > 0) {
            statusUtils.setMessage("修改成功");
        } else {
            statusUtils.setMessage("修改失败");
        }
        return statusUtils;
    }

    // PostMana中的添加角色的接口
    @PostMapping(path = "/addRoles")
    public StatusUtils addRole(@RequestParam("name") String name, @RequestParam("nameZh") String nameZh) {
        Role role = new Role();
        if (!name.startsWith("ROLE_")) {
            role.setName("ROLE_" + name);
        } else {
            role.setName(name);
        }
        role.setNameZh(nameZh);
        int i = roleService.addRole(role);
        if (i > 0) {
            return new StatusUtils("添加成功");
        } else {
            return new StatusUtils("数据异常，请稍后再试");
        }
    }

    // PostMana中的删除角色的接口
    @PostMapping("/deleteRolesById")
    public StatusUtils deleteRoleById(@RequestParam("id")Integer id){
        int i = roleService.deleteRoleById(id);
        if(i!=0){
            return new StatusUtils("删除成功");
        }else {
            return new StatusUtils("删除失败");
        }
    }
}
