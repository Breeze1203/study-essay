package org.javaboy.vhr.service.sal;

import jakarta.annotation.Resource;
import org.javaboy.vhr.bean.Salary;
import org.javaboy.vhr.mapper.SalaryMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @projectName: vhr
 * @package: org.javaboy.vhr.service.sal
 * @className: SalSobService
 * @author: pt3548297839
 * @description: TODO
 * @date: 2023/9/25 19:18
 * @version: 1.0
 */
@Service("SalSobService")
public class SalSobService {
    @Resource(name = "SalaryMapper")
    SalaryMapper salaryMapper;

    public List<Salary> getAllSal(){
        return salaryMapper.selectAll();
    }

    public int insertSalary(Salary salary){
        return salaryMapper.insertSelective(salary);
    }
}
