package org.javaboy.vhr.controller;

import jakarta.annotation.Resource;
import org.javaboy.vhr.bean.Position;
import org.javaboy.vhr.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/pos")
    public List<Position> getAllPosition(){
       return positionService.getAllPosition();
    }


}
