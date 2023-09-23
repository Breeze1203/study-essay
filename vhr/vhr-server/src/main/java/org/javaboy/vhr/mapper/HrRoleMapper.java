package org.javaboy.vhr.mapper;

import org.javaboy.vhr.bean.HrRole;
import org.springframework.stereotype.Repository;

@Repository("HrRoleMapper")
public interface HrRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(HrRole record);

    int insertSelective(HrRole record);

    HrRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HrRole record);

    int updateByPrimaryKey(HrRole record);

    // 给用户绑定所选择的角色 先将之前的角色删除掉
    int insertRoleByHr(Integer hrid,Integer[] rids);

    // 重新绑定角色前，删除掉之前所具有的角色
    int deleteHrRoleByHrid(Integer hrid);
}