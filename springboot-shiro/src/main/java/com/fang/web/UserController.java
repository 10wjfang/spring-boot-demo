package com.fang.web;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @Author: fwj
 * @Description:
 * @Date: Created in 2018/11/6 14:28
 * @Modified by:
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @RequestMapping("/list")
    public String userInfo() {
        return "user";
    }
}
