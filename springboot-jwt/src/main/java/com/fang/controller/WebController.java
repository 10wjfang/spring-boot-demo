package com.fang.controller;

import com.fang.domain.ResponseBean;
import com.fang.domain.UserInfo;
import com.fang.service.UserInfoService;
import com.fang.utils.JWTUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: fwj
 * @Description:
 * @Date: Created in 2018/11/7 14:12
 * @Modified by:
 */
@RestController
public class WebController {
    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping("/login")
    public ResponseBean login(@RequestParam("username") String username, @RequestParam("password") String password) {
        UserInfo userInfo = userInfoService.findByUsername(username);
        if (userInfo.getPassword().equals(password)) {
            return new ResponseBean(200, "success", JWTUtil.sign(username, password));
        }
        else {
            return new ResponseBean(40, "fail", null);
        }
    }

    @RequestMapping("/index")
    public ResponseBean index() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            return new ResponseBean(200, "login", null);
        }
        else {
            return new ResponseBean(200, "guest",null);
        }
    }
}
