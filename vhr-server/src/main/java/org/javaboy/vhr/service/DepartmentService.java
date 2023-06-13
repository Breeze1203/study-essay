package org.javaboy.vhr.service;

import jakarta.annotation.Resource;
import org.javaboy.vhr.bean.Department;
import org.javaboy.vhr.bean.Employee;
import org.javaboy.vhr.mapper.DepartmentMapper;
import org.javaboy.vhr.mapper.EmployeeMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: breeze
 * @Date: 2023/5/28 20:44
 * @Email:3548297839@qq.com
 * @position:Shenzhen China
 */
@Service("DepartmentService")
public class DepartmentService {
    @Resource(name = "DepartmentMapper")
    DepartmentMapper departmentMapper;

    @Resource(name = "EmployeeMapper")
    EmployeeMapper employeeMapper;

    // 查询所有的
    public List<Department> getAllDepartmentByParentId(Integer parentId) {
        return departmentMapper.getAllDepartmentByParentId(parentId);
    }

    // 添加部门
    public int addDepartment(Integer id, String parent, String depPath, String name) {
        Department depart = new Department();
        depart.setName(name);
        depart.setParentId(id);
        depart.setEnabled(true);
        depart.setChildren(new ArrayList<>());
        depart.setParent(false);
        // 先根据父节点的id确认其parentId
        int insert = departmentMapper.insert(depart);
        // 插入之后在找其自己的id
        String departmentId = departmentMapper.findIdByName(name);
        // 修改自己的depPath
        String dep = depPath + "." + departmentId;
        Department department = new Department();
        department.setDepPath(dep);
        department.setId(Integer.parseInt(departmentId));
        int i = departmentMapper.updateByPrimaryKeySelective(department);
        if (parent.equals("false")) {
            // 如果要插入部门的当前父部的isParent为false,则先将其改为true
            Department dp = new Department();
            dp.setParent(true);
            dp.setId(id);
            departmentMapper.updateByPrimaryKeySelective(dp);
        }
        return i;
    }

    // 删除部门
    public int deleteDepartmentById(Integer id) {
        List<Employee> workerByDepartmentId = employeeMapper.findWorkerByDepartmentId(id);
        if (workerByDepartmentId.size() == 0) {
            return departmentMapper.deleteByPrimaryKey(id);
        }
        return -1;
    }
}
