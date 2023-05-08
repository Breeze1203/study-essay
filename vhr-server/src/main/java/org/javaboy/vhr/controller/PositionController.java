package org.javaboy.vhr.controller;

import jakarta.annotation.Resource;
import org.javaboy.vhr.bean.Position;
import org.javaboy.vhr.service.PositionService;
import org.javaboy.vhr.utils.StatusUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @author Breeze
 * @address Shenzhen China
 * @email 3548297839@qq.com
 */
@RestController
@RequestMapping("/api/sys/basic")
public class PositionController {
    @Autowired
    PositionService positionService;

    // 查询所有的职位
    @GetMapping("/pos")
    public List<Position> getAllPosition() {
        return positionService.getAllPosition();
    }

    // 根据id编号删除
    @PostMapping("/deletePositionById")
    public StatusUtils deletePositionById(@RequestParam("id") Integer id) {
        StatusUtils statusUtils = new StatusUtils();
        int result = positionService.deleteByPrimaryKey(id);
        if (result == 1) {
            statusUtils.setStatus(200);
            statusUtils.setMessage("删除成功");
        } else {
            statusUtils.setMessage("删除失败");
        }
        return statusUtils;
    }
    // 添加职位
    @PostMapping("/addPosition")
    public StatusUtils addPosition(@RequestParam("name") String name){
        StatusUtils statusUtils=new StatusUtils();
        Position position = new Position();
        position.setName(name);
        position.setCreateDate(new Date());
        int i = positionService.insertPos(position);
        if(i==1){
            statusUtils.setStatus(200);
            statusUtils.setMessage("添加成功");
        }else {
            System.out.println("添加失败");
        }
        return statusUtils;
    }
}