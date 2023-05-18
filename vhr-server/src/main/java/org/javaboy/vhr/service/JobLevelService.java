package org.javaboy.vhr.service;

import jakarta.annotation.Resource;
import org.javaboy.vhr.bean.JObLevel;
import org.javaboy.vhr.mapper.JObLevelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Breeze
 * @address Shenzhen China
 * @email 3548297839@qq.com
 */
@Service("JobLevelService")
public class JobLevelService {
    @Resource(name = "JobLevelMapper")
    JObLevelMapper jObLevelMapper;

    // 查找所有职称
    public List<JObLevel> getAllJobLevel(){
        return jObLevelMapper.getAllJobLevel();
    }

    // 添加职称
    public int addJobLevel(JObLevel jObLevel){
        return jObLevelMapper.insertSelective(jObLevel);
    }

    // 删除职称
    public int deleteJobLevel(Integer id){
        return jObLevelMapper.deleteByPrimaryKey(id);
    }

    public int updateJobLevel(JObLevel jObLevel){
        return jObLevelMapper.updateByPrimaryKeySelective(jObLevel);
    }
    // 批量删除职位
    public int deleteJobIds(Integer[] ids){
        return jObLevelMapper.deleteJobLevelByIds(ids);
    }
}
