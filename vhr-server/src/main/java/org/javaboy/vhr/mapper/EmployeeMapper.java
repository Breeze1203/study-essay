package org.javaboy.vhr.mapper;

import org.javaboy.vhr.bean.Employee;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("EmployeeMapper")
public interface EmployeeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Employee record);

    int insertSelective(Employee record);

    Employee selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Employee record);

    int updateByPrimaryKey(Employee record);

    // 根据部门的parentId查找员工
    List<Employee> findWorkerByDepartmentId(Integer departmentId);
}