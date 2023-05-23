package org.javaboy.vhr.controller;

import jakarta.annotation.Resource;
import org.javaboy.vhr.bean.Role;
import org.javaboy.vhr.mapper.RoleMapper;
import org.javaboy.vhr.service.RoleService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Breeze
 * @address Shenzhen China
 * @email 3548297839@qq.com
 */
// 权限组
@RestController
@RequestMapping("/api/sys/permission")
public class PermissionController {
    @Resource(name = "RoleService")
    RoleService roleService;

    @RequestMapping("/getAllRoles")
    public List<Role> getAllRoles(){
        return roleService.getAllRoles();
    }

}
