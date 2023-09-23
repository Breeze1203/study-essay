package org.javaboy.vhr.mapper;

import org.javaboy.vhr.bean.Politicsstatus;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("PoliticsStatusMapper")
public interface PoliticsstatusMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Politicsstatus record);

    int insertSelective(Politicsstatus record);

    Politicsstatus selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Politicsstatus record);

    int updateByPrimaryKey(Politicsstatus record);

    List<Politicsstatus> AllPoliticsStatus();
}