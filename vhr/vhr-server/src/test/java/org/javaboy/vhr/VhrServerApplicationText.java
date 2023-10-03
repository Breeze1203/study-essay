package org.javaboy.vhr;


import org.javaboy.vhr.bean.Employee;
import org.javaboy.vhr.bean.Salary;
import org.javaboy.vhr.mapper.EmployeeMapper;
import org.javaboy.vhr.mapper.SalaryMapper;
import org.javaboy.vhr.rocketmq.ProducerMailService;
import org.javaboy.vhr.service.emp.EmployeeService;
import org.javaboy.vhr.service.sal.SobCfgService;
import org.javaboy.vhr.utils.EmpUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


/**
 * Unit test for simple VhrServerApplication.
 */
@SpringBootTest
public class VhrServerApplicationText{
    @Autowired
    SalaryMapper salaryMapper;

    @Autowired
    ProducerMailService producerMailService;

    @Autowired
    EmployeeMapper employeeMapper;
    @Autowired
    EmployeeService employeeService;

    @Autowired
    SobCfgService service;

    @Test
    void contextLoads() {
        Salary salary = new Salary();
        salary.setName("www");
        salary.setAccumulationFundPer(0.8);
        salary.setAllSalary(1);
        salary.setBonus(2);
        salary.setMedicalBase(2);
        salary.setMedicalPer(0.8);
        salary.setAccumulationFundBase(2);
        salary.setAccumulationFundPer(3);
        salary.setLunchSalary(3);
        salary.setBasicSalary(3);
        int i = salaryMapper.insertSelective(salary);
        System.out.println(i);
    }

    @Test
    void test(){
        EmpUtil epmWithSalary = service.getEpmWithSalary(15, 1);
        System.out.println(epmWithSalary.getTotal());
//        long count = employeeMapper.getCount();
//        System.out.println(count);
    }
}
