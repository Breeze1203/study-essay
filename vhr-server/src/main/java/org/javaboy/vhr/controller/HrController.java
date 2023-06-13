package org.javaboy.vhr.controller;

import jakarta.annotation.Resource;
import org.javaboy.vhr.bean.Hr;
import org.javaboy.vhr.mapper.HrMapper;
import org.javaboy.vhr.mapper.HrRoleMapper;
import org.javaboy.vhr.service.HrRoleService;
import org.javaboy.vhr.utils.HrUtil;
import org.javaboy.vhr.utils.StatusUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 登录用户信息展示的controller
 *
 * @author breeze
 * @date 2023/6/3
 * @Email 3548297839@qq.com
 * @position Shenzhen China
 */
@RestController
@RequestMapping("/api/sys/hr")
public class HrController {
    @Resource(name = "Hr")
    HrMapper hrMapper;

    @Resource(name = "HrRoleService")
    HrRoleService hrRoleService;

    @PostMapping("/getAllHr")
    // 这里表示该请求参数可传递可不传递
    public List<Hr> getAllHr(@RequestParam(required = false, value = "keyword") String keyword) {
        return hrMapper.getAllHr(HrUtil.getCurrentHr().getId(), keyword);
    }

    // 更改用户状态
    @PostMapping("/updateEnabled")
    public StatusUtils updateEnable(@RequestParam("enabled") String enabled, @RequestParam("id") Integer id) {
        int i;
        if (enabled.equals("true")) {
            i = hrMapper.updateEnable(1, id);
        } else {
            i = hrMapper.updateEnable(0, id);
        }
        if (i == 1) {
            return new StatusUtils("更新成功");
        } else {
            return new StatusUtils("更新失败");
        }
    }

    // 更改用户所具有的角色，先将之前的角色删除掉，然后重新添加
    @PostMapping("/updateHrRole")
    public StatusUtils updateHrRole(@RequestParam(value = "hrid") Integer hrid, @RequestParam("rids") Integer[] rid) {
        int i = hrRoleService.InsertHrRole(hrid, rid);
        if (i > 0) return new StatusUtils("修改成功");
        return new StatusUtils("修改失败，请稍后重试");
    }

    // 删除hr用户同时删除之前有的角色
    @PostMapping("/deleteHr")
    public StatusUtils deleteHrById(@RequestParam("HrId") Integer id) {
        int i = hrMapper.deleteById(id);
        int res = hrRoleService.deleteHrRole(id);
        if (i > 0 && res >= 0) return new StatusUtils("删除成功");
        return new StatusUtils("删除失败");
    }
}
