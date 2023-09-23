package org.javaboy.vhr.mapper;

import org.javaboy.vhr.bean.Nation;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("NationMapper")
public interface NationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Nation record);

    int insertSelective(Nation record);

    Nation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Nation record);

    int updateByPrimaryKey(Nation record);

    //    查询所有部门
    List<Nation> AllNation();
}