package org.javaboy.vhr.mapper;

import org.javaboy.vhr.bean.Department;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("DepartmentMapper")
public interface DepartmentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Department record);

    int insertSelective(Department record);

    Department selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Department department);

    int updateByPrimaryKey(Department record);

    // 根据parentId查询所有部门
    List<Department> getAllDepartmentByParentId(Integer parentId);

    String findIdByName(String name);

//    查询所有部门
    List<Department> allDepartment();

}