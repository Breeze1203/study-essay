package org.javaboy.vhr.service;

import jakarta.annotation.Resource;
import org.javaboy.vhr.bean.Position;
import org.javaboy.vhr.mapper.PositionMapper;
import org.javaboy.vhr.utils.StatusUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Breeze
 * @address Shenzhen China
 * @email 3548297839@qq.com
 */
@Service
public class PositionService {
    @Resource(name = "PositionMapper")
    PositionMapper positionMapper;

    //查找职位
    public List<Position> getAllPosition(){
        return positionMapper.getAllPosition();
    }

    // 添加职位
    public int insertPos(Position position){
        return positionMapper.insertSelective(position);
    }

    // 删除职位
    public int deleteByPrimaryKey(Integer id){
        return positionMapper.deleteByPrimaryKey(id);
    }

    // 修改职位
    public int upDatePosition(Position position){
       return positionMapper.updateByPrimaryKeySelective(position);
    }
}
