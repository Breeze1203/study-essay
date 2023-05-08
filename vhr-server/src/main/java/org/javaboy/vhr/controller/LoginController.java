package org.javaboy.vhr.controller;


import org.javaboy.vhr.utils.StatusUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @GetMapping("/api/hello")
    public String hello(){
        return "hello";
    }

    @GetMapping("/login")
    public StatusUtils login(){
        return new StatusUtils("尚未登录,请登录",500);
    }
}
