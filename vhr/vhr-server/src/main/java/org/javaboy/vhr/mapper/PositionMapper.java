package org.javaboy.vhr.mapper;

import org.javaboy.vhr.bean.Position;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("PositionMapper")
public interface PositionMapper {
    int deleteByPrimaryKey(Integer id);

    //查询所有
    List<Position> getAllPosition();

    int insertSelective(Position record);

    int updateByPrimaryKeySelective(Position record);

    // 批量删除
    int deleteByIds(Integer[] ids);
}