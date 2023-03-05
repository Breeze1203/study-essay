package org.javaboy.vhr.mapper;

import org.javaboy.vhr.bean.Menu;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;


@Repository("MenuMapper")
public interface MenuMapper {

    /*根据当前登录用户查询用户菜单*/
    List<Menu> getMenuByHrId(Integer id);
}