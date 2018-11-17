package com.fang.controller;

import com.fang.domain.UserEntity;
import org.apache.catalina.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: fwj
 * @Description:
 * @Date: Created in 2018/11/2 12:09
 * @Modified by:
 */
@RestController
public class HelloController {
    @GetMapping("/hello")
    public String sayHello() {
        return "hello world";
    }

    @GetMapping("/user")
    public UserEntity user() {
        return new UserEntity();
    }

    @GetMapping("/hello/{name}")
    public String sayHi(@PathVariable("name") String name) {
        return "hello " + name;
    }

    @GetMapping("/hello2")
    public String sayHi2(@RequestParam("name") String name) {
        return "hello " + name;
    }

}
