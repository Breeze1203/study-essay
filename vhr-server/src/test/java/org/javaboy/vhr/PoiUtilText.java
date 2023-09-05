package org.javaboy.vhr;

import jakarta.annotation.Resource;
import org.javaboy.vhr.bean.Employee;
import org.javaboy.vhr.bean.Nation;
import org.javaboy.vhr.bean.Politicsstatus;
import org.javaboy.vhr.mapper.*;
import org.javaboy.vhr.service.emp.EmployeeService;
import org.javaboy.vhr.utils.DateUtil;
import org.javaboy.vhr.utils.EmpUtil;
import org.javaboy.vhr.utils.PoiUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class PoiUtilText {


    // 这个是政治面貌
    @Resource(name = "PoliticsStatusMapper")
    PoliticsstatusMapper politicsstatusMapper;

    // 所属名族
    @Resource(name = "NationMapper")
    NationMapper nationMapper;

    // 查询所有职位
    @Resource(name = "PositionMapper")
    PositionMapper positionMapper;

    // 所有职称
    @Resource(name = "JobLevelMapper")
    JObLevelMapper jObLevelMapper;

    // 所有部门
    @Resource(name = "DepartmentMapper")
    DepartmentMapper departmentMapper;

    @Autowired
    EmployeeService employeeService;

    @Test
    void text(){
//        EmpUtil allUtil = employeeService.getAllUtil();
//        System.out.println(allUtil);
        Employee employee = new Employee();
        Field[] fields = employee.getClass().getDeclaredFields();
        int numOfFields = fields.length;
        System.out.println(numOfFields);
//        List<Employee> employees = PoiUtil.importExcel(file, allUtil);
//        int size = employeeService.insertByList(employees);
    }
}
