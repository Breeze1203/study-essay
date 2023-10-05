package org.javaboy.vhr.mapper;

import org.apache.ibatis.annotations.Param;
import org.javaboy.vhr.bean.Salary;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("SalaryMapper")
public interface SalaryMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(Salary record);

    // 查询所有工资套帐
    List<Salary> selectAll();

    //修改salary
    int updateSal(Salary salary);

    // 根据名字查询套账
    List<Salary> getSalByName(@Param("name") String name);

    // 查询所有套账的名字及id
    List<Salary> getSalNameAndId();
}