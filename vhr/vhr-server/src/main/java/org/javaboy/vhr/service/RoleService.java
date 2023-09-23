package org.javaboy.vhr.service;

import jakarta.annotation.Resource;
import org.javaboy.vhr.bean.Role;
import org.javaboy.vhr.mapper.RoleMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Breeze
 * @address Shenzhen China
 * @email 3548297839@qq.com
 */
@Service(value = "RoleService")
public class RoleService {
    @Resource(name = "RoleMapper")
    RoleMapper roleMapper;

    public List<Role> getAllRoles(){
        return roleMapper.getAllRoles();
    }

    public int addRole(Role role){
        return roleMapper.insert(role);
    }

    public int deleteRoleById(Integer id){
        return roleMapper.deleteByPrimaryKey(id);
    }
}
