package org.javaboy.vhr.mapper;

import org.apache.ibatis.annotations.Param;
import org.javaboy.vhr.bean.EmpSalary;
import org.springframework.stereotype.Repository;

@Repository("EmpSalaryMapper")
public interface EmpSalaryMapper {

    int insertSalByEid(@Param("sid") Integer sid,@Param("eid") Integer eid);

    // 根据eid修改sid
    int updateByEid(@Param("sid") Integer sid,@Param("eid") Integer eid);
}