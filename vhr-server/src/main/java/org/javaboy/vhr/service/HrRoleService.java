package org.javaboy.vhr.service;

import jakarta.annotation.Resource;
import org.javaboy.vhr.mapper.HrRoleMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户所具有角色绑定的crud
 *
 * @author breeze
 * @date 2023/6/6
 * @Email 3548297839@qq.com
 * @position Shenzhen China
 */
@Service("HrRoleService")
public class HrRoleService {
    @Resource(name = "HrRoleMapper")
    HrRoleMapper hrRoleMapper;

    @Transactional
    public int InsertHrRole(Integer hrid, Integer[] rids) {
        // 先删除之前有的角色
        hrRoleMapper.deleteHrRoleByHrid(hrid);
        return hrRoleMapper.insertRoleByHr(hrid, rids);
    }

    // 删除hr用户所具有的角色
    public int deleteHrRole(Integer hrid) {
        return hrRoleMapper.deleteHrRoleByHrid(hrid);

    }
}
