package com.bigdata.gxbuser.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 测试
 *
 * @author fwj
 * @date 2019-02-13 17:37
 **/
@PreAuthorize("hasAuthority('管理员')")
@RestController
public class TestController {
    @RequestMapping("/test")
    public String test(HttpServletRequest request) {
        return "hello, " + SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
