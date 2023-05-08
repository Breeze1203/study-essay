package org.javaboy.vhr.mapper;

import org.javaboy.vhr.bean.Position;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("PositionMapper")
public interface PositionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Position record);
    //查询所有
    List<Position> getAllPosition();

    int insertSelective(Position record);

    Position selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Position record);

    int updateByPrimaryKey(Position record);
}