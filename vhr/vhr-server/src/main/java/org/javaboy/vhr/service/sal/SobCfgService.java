package org.javaboy.vhr.service.sal;

import jakarta.annotation.Resource;
import jakarta.validation.constraints.Email;
import org.javaboy.vhr.bean.Employee;
import org.javaboy.vhr.bean.Salary;
import org.javaboy.vhr.mapper.EmpSalaryMapper;
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

    @Resource(name = "EmpSalaryMapper")
    EmpSalaryMapper empSalaryMapper;

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

    // 根据用户的id修改账套id
    public Integer updateSidByEid(Integer sid,Integer eid){
        return empSalaryMapper.updateByEid(sid,eid);
    }

    // 根据用户id插入账套id
    public Integer insetSidByEid(Integer sid,Integer eid){
        return empSalaryMapper.insertSalByEid(sid, eid);
    }
}
