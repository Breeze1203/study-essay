package org.javaboy.vhr.service.sal;

import jakarta.annotation.Resource;
import jakarta.validation.constraints.Email;
import org.javaboy.vhr.bean.Employee;
import org.javaboy.vhr.mapper.EmployeeMapper;
import org.javaboy.vhr.utils.EmpByPageUtil;
import org.javaboy.vhr.utils.EmpUtil;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @projectName: vhr
 * @package: org.javaboy.vhr.service.sal
 * @className: 员工工资套账组件页面数据查询
 * @author: pt3548297839
 * @description: TODO
 * @date: 2023/10/3 10:39
 * @version: 1.0
 */

@Service("SobCfgService")
public class SobCfgService {
    @Resource(name = "EmployeeMapper")
    EmployeeMapper employeeMapper;

    public EmpUtil getEpmWithSalary(Integer size,Integer page){
        if (size != null && page != null) {
            page = (page - 1) * size;
        }
        List<Employee> employees = employeeMapper.selectEmpWithSalary(size,page);
        EmpUtil empUtil = new EmpUtil();
        // 总条数
        long count = employeeMapper.getCount();
        empUtil.setEmployeeList(employees);
        empUtil.setTotal(count);
        return empUtil;
    }

}
