package org.javaboy.vhr.controller.emp;

import jakarta.annotation.Resource;
import org.javaboy.vhr.bean.Employee;
import org.javaboy.vhr.service.emp.EmployeeService;
import org.javaboy.vhr.utils.EmpUtil;
import org.javaboy.vhr.utils.PoiUtil;
import org.javaboy.vhr.utils.StatusUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/employee/basic")
public class EmployeeController {
    @Resource(name = "EmployeeService")
    EmployeeService employeeService;

    @PostMapping("/")
    public EmpUtil getAllEmp(@RequestParam(name = "page") Integer page,
                             @RequestParam(name = "size") Integer size, @RequestParam(name = "keyword") String keyword) {
        return employeeService.getEmployeeByPage(size, page, keyword);
    }

    // 添加员工
    @PostMapping("/addEmp")
    public StatusUtils addEmp(@RequestBody Employee employee) {
        Integer integer = employeeService.addEmployee(employee);
        if (integer > 0) return new StatusUtils("添加成功");
        return new StatusUtils("添加失败，请稍后再试");
    }

    // 删除员工 @PathVariable 注解从 URI 中提取 id 参数
    @DeleteMapping("/{id}")
    public StatusUtils deleteEmp(@PathVariable Integer id) {
        Integer integer = employeeService.deleteEmp(id);
        if (integer == 1) return new StatusUtils("删除成功");
        return new StatusUtils("删除失败");
    }

    // 修改员工
    @PutMapping("/updateEmp")
    public StatusUtils updateEmp(@RequestBody Employee employee) {
        Integer integer = employeeService.updateEmp(employee);
        if (integer == 1) return new StatusUtils("修改成功");
        return new StatusUtils("修改失败");
    }


    // 下载文件
    @GetMapping("/downExcel")
    public ResponseEntity<byte[]> downExcel() {
        try {
            EmpUtil employeeByPage = employeeService.getEmployeeByPage(null, null, null);
            return PoiUtil.downExcel(employeeByPage.getEmployeeList());
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    // 上传文件
    @PostMapping("/upload")
    public StatusUtils upload(@RequestParam("file") MultipartFile file) {
        // file.transferTo是Spring框架中MultipartFile接口提供的一个方法，
        // 用于将上传的文件内容保存到指定的目标文件中
//        try { 执行完这段代码后，不能 对file这个对象进行操作
//            file.transferTo(new File("D:\\"+file.getOriginalFilename()));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        PoiUtil.importExcel(file,new ArrayList<>());
        EmpUtil allUtil = employeeService.getAllUtil();
        List<Employee> employees = PoiUtil.importExcel(file, allUtil);
        for (Employee e : employees
        ) {
            System.out.println(e.getPosId());
        }
        int size = employeeService.insertByList(employees);
        if (size > 0) {
            return new StatusUtils("导入成功");
        }
        return new StatusUtils("导入失败，请稍后再试！");
    }
}
