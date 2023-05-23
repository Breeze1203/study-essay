package org.javaboy.vhr;

import jakarta.annotation.Resource;
import org.javaboy.vhr.bean.*;
import org.javaboy.vhr.mapper.HrMapper;
import org.javaboy.vhr.service.JobLevelService;
import org.javaboy.vhr.service.MenuService;
import org.javaboy.vhr.service.PositionService;
import org.javaboy.vhr.service.RoleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.AntPathMatcher;

import java.util.Date;
import java.util.List;

@SpringBootTest
class VhrServerApplicationTests {

    AntPathMatcher antPathMatcher=new AntPathMatcher();

    @Autowired
    PositionService positionService;
    @Autowired
    HrMapper hrMapper;

    @Autowired
    JobLevelService jobLevelService;
    @Autowired
    MenuService menuService;
    @Test
    void contextLoads() {
        Hr admin = hrMapper.findHrByUsername("admin");
        System.out.println(admin);
        // 获取菜单的所有角色
        List<Menu> allMenusWithRole = menuService.getAllMenusWithRole();
        for (Menu menu : allMenusWithRole) {
            if(antPathMatcher.match(menu.getUrl(),"/salary/sob/**")){
                List<Role> roles=menu.getRole();
                System.out.println(roles);
            };
            //System.out.println(menu.getRole());
        }
        //System.out.println(allMenusWithRole);
        System.out.println(allMenusWithRole.size());
    }

    @Test
    void text1() {
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

    @Test
    void text2(){
        int result = hrMapper.updateHr("张程");
        System.out.println(result);
    }
    @Test
    void text3() {
        int i = hrMapper.deleteById(13);
        System.out.println(i);
    }

    @Test
    void text4() {
        Position position = new Position();
        position.setId(56);
        position.setName("李四");
        position.setCreateDate(new Date());
        position.setEnabled(true);
//        positionService.insertPosition(position);
//        int i = positionService.deleteByPrimaryKey(56);
//        System.out.println(i);
//        List<Position> allPosition = positionService.getAllPosition();
//        System.out.println(allPosition);
    }

    @Test //查询所有职称
    void text5() {
        List<JObLevel> allJobLevel = jobLevelService.getAllJobLevel();
        System.out.println(allJobLevel);
    }

    @Test //添加职称
    void text6() {
        JObLevel jObLevel=new JObLevel();
        //jObLevel.setId(99);
        jObLevel.setName("架构师");
        jObLevel.setEnabled(true);
        jObLevel.setCreateDate(new Date());
        int i = jobLevelService.addJobLevel(jObLevel);
        System.out.println(i);
    }
    @Resource(name = "RoleService")
    RoleService roleService;
    @Test //查看所有角色
    void text7(){
        List<Role> allRoles = roleService.getAllRoles();
        System.out.println(allRoles);
    }

    @Test //查看所有菜单
    void text8(){
        List<Menu> allMenu = menuService.getAllMenu();
        System.out.println(allMenu);
    }

    @Test //查看不同角色所拥有的menu
    void text9(){
        List<Integer> menusByRole = menuService.getMenusByRole(1);
        System.out.println(menusByRole.size());
    }
}
