package org.javaboy.vhr.mapper;

import org.apache.ibatis.annotations.Param;
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

    // 分页查询员工
    List<Employee> SelectEmpByPage(@Param("size") Integer size, @Param("page") Integer page,@Param("keyword")String keyword);

    // 查询数据的总条数
    long getEmpCount(@Param("keyword")String keyword);

    //根据集合插入
    int insertByList(@Param("list")List<Employee> list);
}