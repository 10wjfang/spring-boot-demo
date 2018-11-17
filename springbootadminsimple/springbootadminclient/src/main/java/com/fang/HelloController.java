package com.fang;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: fwj
 * @Description:
 * @Date: Created in 2018/5/2 14:38
 * @Modified by:
 */
@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello(){
        return "Hello Spring Boot Admin";
    }
}
