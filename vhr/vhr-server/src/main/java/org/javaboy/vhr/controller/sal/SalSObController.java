package org.javaboy.vhr.controller.sal;

import jakarta.annotation.Resource;
import org.javaboy.vhr.bean.Salary;
import org.javaboy.vhr.service.sal.SalSobService;
import org.javaboy.vhr.utils.StatusUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @projectName: vhr
 * @package: org.javaboy.vhr.controller.sal
 * @className: SalSObController
 * @author: pt3548297839
 * @description: TODO
 * @date: 2023/9/25 19:21
 * @version: 1.0
 */
@RestController
@RequestMapping("/api/sal/sob")
public class SalSObController {
    @Resource(name = "SalSobService")
    SalSobService salSobService;

    @GetMapping("/")
    public List<Salary> getAllSal(){
        return salSobService.getAllSal();
    }

    @PostMapping("/")
    public StatusUtils insertSal(@RequestBody Salary salary){
        salary.setCreateDate(new Date());
        int i = salSobService.insertSalary(salary);
        if(i>0) {
            return new StatusUtils("添加成功");
        }else {
            return new StatusUtils("添加失败,请稍后再试");
        }
    }
}
