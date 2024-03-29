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
    List<Employee> SelectEmpByPage(@Param("size") Integer size, @Param("page") Integer page, @Param("keyword") String keyword);

    // 查询数据的总条数
    long getEmpCount(@Param("keyword") String keyword);

    //根据集合插入
    int insertByList(@Param("list") List<Employee> list);

    //    高级搜索
    List<Employee> SelectEmpByPageAdvanced(@Param("size") Integer size, @Param("page") Integer page, @Param("emp") Employee e);

    // 高级搜索查询总条数
    long getEmpCountAdvanch(@Param("emp") Employee employee);

    // 根据employee的id进行批量删除
    long deleteEmployeeByIds(@Param("ids")Integer[] ids);
    Employee selectEmpByWorkId(@Param(("workId"))String workID);
    // 员工套账搜素
    List<Employee> selectEmpWithSalary(@Param("size") Integer size, @Param("page") Integer page);
    // 查询总条数
    long getCount();


}