package org.javaboy.vhr.mapper;

import org.javaboy.vhr.bean.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("RoleMapper")
public interface RoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    // 查询所有角色
    List<Role> getAllRoles();
}