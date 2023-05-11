package org.javaboy.vhr.mapper;

import org.javaboy.vhr.bean.JObLevel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("JobLevelMapper")
public interface JObLevelMapper {
    // 删除职位
    int deleteByPrimaryKey(Integer id);


    // 添加职位
    int insertSelective(JObLevel record);


    // 更新职称
    int updateByPrimaryKeySelective(JObLevel record);
    

    // 查询所有职称
    List<JObLevel> getAllJobLevel();

    //批量删除
    int deleteJobLevelByIds(Integer[] ids);
}