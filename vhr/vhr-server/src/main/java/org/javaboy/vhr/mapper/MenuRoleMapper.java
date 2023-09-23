package org.javaboy.vhr.mapper;

import org.javaboy.vhr.bean.MenuRole;
import org.springframework.stereotype.Repository;

@Repository("MenuRoleMapper")
public interface MenuRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MenuRole record);

    int insertSelective(MenuRole record);

    MenuRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MenuRole record);

    int updateByPrimaryKey(MenuRole record);

    // 角色关联表插入数据
    int insertRoleMenu(Integer[] mids,int rid);

    // 删除角色所拥有的菜单
    int deleteMenuByRole(int rid);
}