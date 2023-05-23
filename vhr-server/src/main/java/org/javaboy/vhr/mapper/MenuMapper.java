package org.javaboy.vhr.mapper;

import org.javaboy.vhr.bean.Menu;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("MenuMapper")
public interface MenuMapper {

    /*根据当前登录用户查询用户菜单*/
    List<Menu> getMenuByHrId(Integer id);

    // 查询所有菜单
    List<Menu> getAllMenu();
    List<Menu> getAllMenusWithRole();

    // 根据角色查询可以访问的资源
    List<Integer> getMenusByRole(Integer mid);

}