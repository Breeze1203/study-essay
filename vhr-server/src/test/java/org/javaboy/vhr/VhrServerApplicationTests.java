package org.javaboy.vhr;

import jakarta.annotation.Resource;
import org.javaboy.vhr.bean.*;
import org.javaboy.vhr.mapper.DepartmentMapper;
import org.javaboy.vhr.mapper.EmployeeMapper;
import org.javaboy.vhr.mapper.HrMapper;
import org.javaboy.vhr.mapper.HrRoleMapper;
import org.javaboy.vhr.service.*;
import org.javaboy.vhr.service.emp.EmployeeService;
import org.javaboy.vhr.utils.EmpUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;

import java.util.ArrayList;
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
    DepartmentService departmentService;

    @Autowired
    DepartmentMapper departmentMapper;

    @Autowired
    JobLevelService jobLevelService;
    @Autowired
    MenuService menuService;


    @Autowired
    EmployeeMapper employeeMapper;
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

    @Test //给不同的角色插入不同的mid
    void text10(){
        Integer[] integers = new Integer[]{1, 23, 5};
        System.out.println(integers);
        int i = menuService.insertRoleMenu(new Integer[]{2, 3, 4, 9}, 14);
        System.out.println(i);
    }
    @Test
    void text11(){
        List<Department> allDepartmentByParentId = departmentService.getAllDepartmentByParentId(-1);
        System.out.println(allDepartmentByParentId);
    }

    // 根据id查询所有部门
    @Test
    void text12(){
//        Department department = departmentMapper.selectByPrimaryKey(92);
//        String depPath = department.getDepPath();
//        System.out.println(depPath);
//        Department department1 = new Department();
//        department1.setName("运维3部");
//        department1.setChildren(new ArrayList<>());
//        department1.setEnabled(true);
//        department1.setParentId(92);
//        int insert = departmentMapper.insert(department1);
//        System.out.println(insert);
//        int de= departmentMapper.findIdByName("运维3部");
//        System.out.println(de);
//        int res = departmentService.addDepartment(92, ".1.4.5.92", "运维4部");
//        System.out.println(res);
//        Department department=departmentMapper.selectByPrimaryKey(93);
//        String depPath = department.getDepPath();
//        System.out.println(depPath);
//        Department department1 = new Department();
//
    }

//    根据部门的parentId查找员工
    @Test
    void text13(){
        List<Employee> workerByParentId = employeeMapper.findWorkerByDepartmentId(91);
        System.out.println(workerByParentId);
    }
    @Test
    void text14(){
        List<Hr> allHr = hrMapper.getAllHr(3,"彭");
        System.out.println(allHr.size());
    }

    @Resource(name = "HrRoleMapper")
    HrRoleMapper hrRoleMapper;
    // 给用户添加角色
    @Test
    void text15(){
//        int i = hrRoleMapper.insertRoleByHr(3, new Integer[]{6, 5});
        int i = hrRoleMapper.deleteHrRoleByHrid(3);
        System.out.println(i);
    }

    @Resource(name = "EmployeeService")
    EmployeeService employeeService;
    // 分页查询员工
    @Test
    void text16(){
//        long empCount = employeeMapper.getEmpCount("江");
//        System.out.println(empCount);
//        EmpUtil res = employeeService.getEmployeeByPage(10, 1, "江");
//        System.out.println(res);
//        List<Employee> employees = employeeMapper.SelectEmpByPage(15,20);
//        System.out.println(employees.get(14));
//        List<Employee> employeeByPage = employeeService.getEmployeeByPage(25,2);
//        System.out.println(employeeByPage.get(0));
//        System.out.println(employeeByPage.get(24));
//        System.out.println(employeeByPage.size());
//        Employee employee = new Employee();
//        employee.setBegindate(new Date());
//        employee.setBeginContract(new Date());
//        employee.setContractTerm(2.0);
//        employee.setAddress("中国深圳");
//        employee.setBegindate(new Date());
//        employee.setEmail("2321");
//        employee.setBirthday(new Date());
//        Integer integer = employeeService.addEmployee(employee);
//        System.out.println(employee);
        Employee employee = new Employee();
        employee.setPosId(29);
        employee.setPoliticId(13);
        employee.setNationId(1);
        employee.setJobLevelId(9);
        employee.setEngageForm("劳务合同");
        employee.setDepartmentId(5);
        List<Employee> employees = employeeMapper.SelectEmpByPageAdvanced(10, 1, employee);
        long empCountAdvanch = employeeMapper.getEmpCountAdvanch(employee);
        System.out.println(empCountAdvanch);
        System.out.println(employees);
    }

    @Test
    void text17(){
//        Employee employee = new Employee();
//        employee.setPosId(29);
//        employee.setPoliticId(13);
//        employee.setNationId(1);
//        employee.setJobLevelId(9);
//        employee.setEngageForm("劳务合同");
//        employee.setDepartmentId(5);
//        EmpUtil employeeByPageAdvanch = employeeService.getEmployeeByPageAdvanch(10, 1, employee);
//        System.out.println(employeeByPageAdvanch);
        Integer[] integers = new Integer[]{3794,3795,3796,3707};
        long l = employeeMapper.deleteEmployeeByIds(integers);
        System.out.println(l);
    }
}
