package com.fang.web;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: fwj
 * @Description:
 * @Date: Created in 2018/4/26 14:49
 * @Modified by:
 */
@Controller
@RequestMapping("/userInfo")
public class UserInfoController {
    @RequestMapping("/userList")
    @RequiresPermissions("userInfo:view")
    public String userInfo() {
        return "/userInfo";
    }

    @RequestMapping("/userAdd")
    @RequiresPermissions("userInfo:add")
    public String userInfoAdd() {
        return "userInfoAdd";
    }

    @RequestMapping("/userDel")
    @RequiresPermissions("userInfo:del")
    public String userInfoDel() {
        return "userInfoDel";
    }
}
