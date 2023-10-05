package org.javaboy.vhr.controller.sal;

import jakarta.annotation.Resource;
import org.javaboy.vhr.service.sal.SobCfgService;
import org.javaboy.vhr.utils.EmpUtil;
import org.javaboy.vhr.utils.StatusUtils;
import org.springframework.web.bind.annotation.*;

/**
 * @projectName: vhr
 * @package: org.javaboy.vhr.controller.emp
 * @className: SobCfgController
 * @author: pt3548297839
 * @description: TODO
 * @date: 2023/10/3 10:48
 * @version: 1.0
 */
@RestController
@RequestMapping("/api/salary/sob")
public class SobCfgController {
    @Resource(name = "SobCfgService")
    SobCfgService sobCfgService;
    @GetMapping("/")
    public EmpUtil getAllSalary(@RequestParam("size") Integer size, @RequestParam("page")Integer page){
        return sobCfgService.getEpmWithSalary(size,page);
    }

    // 根据eid修改sid
    @PostMapping("/updateSalSid")
    public StatusUtils updateSal(@RequestParam("sid") Integer sid, @RequestParam("eid") Integer eid){
        Integer result = sobCfgService.updateSidByEid(sid, eid);
        if(result>0){
            return  new StatusUtils("修改成功");
        }else {
            return new StatusUtils("修改失败,请稍后重试");
        }
    }
    // 根据eid插入sid
    @PostMapping("/insertSid")
    public StatusUtils insertSid(@RequestParam("sid") Integer sid, @RequestParam("eid") Integer eid){
        Integer result = sobCfgService.insetSidByEid(sid,eid);
        if(result>0){
            return  new StatusUtils("添加成功");
        }else {
            return new StatusUtils("添加失败,请稍后重试");
        }
    }
}
