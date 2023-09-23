package org.javaboy.vhr.controller;

import jakarta.annotation.Resource;
import org.javaboy.vhr.bean.Department;
import org.javaboy.vhr.service.DepartmentService;
import org.javaboy.vhr.utils.StatusUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Auther: breeze
 * @Date: 2023/5/28 22:56
 * @Email:3548297839@qq.com
 * @position:Shenzhen China
 */
@RestController
@RequestMapping("/api/sys/department")
public class DepartmentController {
    @Resource(name = "DepartmentService")
    DepartmentService departmentService;

    // 查询所有的部门
    @GetMapping("/getAllDepartmentByParentId")
    public List<Department> getAllDepartmentByParentId(){
        return departmentService.getAllDepartmentByParentId(-1);
    }

    // 添加部门
    @PostMapping("/addDepartment")
    public StatusUtils addDepartment(@RequestParam("id")Integer id,@RequestParam("parent")String parent,@RequestParam("name")String name,@RequestParam("depPath")String depPath){
        int i = departmentService.addDepartment(id,parent, depPath, name);
        if(i==1){
            return new StatusUtils("部门添加成功");
        }else {
            return new StatusUtils("添加失败,请稍后再试");
        }
    }

    // 删除部门
    @PostMapping("/deleteDepartment")
    public StatusUtils deleteDepartment(@RequestParam("id")Integer id){
        int i = departmentService.deleteDepartmentById(id);
        if(i==1){
            return new StatusUtils("部门删除成功");
        }
        return new StatusUtils("部门删除失败,该部门下存在员工");
    }
}
