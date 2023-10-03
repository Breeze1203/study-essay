package org.javaboy.vhr.controller.emp;

import jakarta.annotation.Resource;
import org.javaboy.vhr.service.sal.SobCfgService;
import org.javaboy.vhr.utils.EmpUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
