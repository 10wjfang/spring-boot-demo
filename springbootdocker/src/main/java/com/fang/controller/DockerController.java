package com.fang.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: fwj
 * @Description:
 * @Date: Created in 2018/5/4 10:09
 * @Modified by:
 */
@RestController
public class DockerController {
    @RequestMapping("/")
    public String index() {
        return "Hello Docker";
    }
}
