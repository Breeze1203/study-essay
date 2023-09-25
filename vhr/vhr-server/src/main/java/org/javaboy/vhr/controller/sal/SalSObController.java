package org.javaboy.vhr.controller.sal;

import jakarta.annotation.Resource;
import org.javaboy.vhr.bean.Salary;
import org.javaboy.vhr.service.sal.SalSobService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping("/")
    public List<Salary> getAllSal(){
        return salSobService.getAllSal();
    }
}
