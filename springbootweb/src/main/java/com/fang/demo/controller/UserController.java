package com.fang.demo.controller;

import com.fang.demo.domain.User;
import com.fang.demo.service.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.UUID;

@RestController
public class UserController {

    @Autowired
    private UserDao userDao;

    @RequestMapping("/user")
    @Cacheable(value = "user-key")
    public User getUser(String name) {
        User user = userDao.findUserByUsername(name).get(0);
        System.out.println("若下面没出现“无缓存的时候调用”字样且能打印出数据表示测试成功");
        return user;
    }

    @RequestMapping("/uid")
    String uid(HttpSession session) {
        UUID uid = (UUID) session.getAttribute("uid");
        if(uid == null) {
            uid = UUID.randomUUID();
        }
        session.setAttribute("uid",uid);
        return session.getId();
    }
}
