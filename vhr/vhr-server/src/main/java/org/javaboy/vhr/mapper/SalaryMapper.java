package org.javaboy.vhr.mapper;

import org.javaboy.vhr.bean.Salary;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("SalaryMapper")
public interface SalaryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Salary record);

    int insertSelective(Salary record);

    Salary selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Salary record);

    int updateByPrimaryKey(Salary record);

    // 查询所有工资套帐
    List<Salary> selectAll();
}