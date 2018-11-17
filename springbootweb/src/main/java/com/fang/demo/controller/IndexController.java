package com.fang.demo.controller;

import com.fang.demo.domain.Message;
import com.fang.demo.domain.User;
import com.fang.demo.service.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private UserDao userDao;

    @RequestMapping("/")
    public String Index(Model model,
                        @RequestParam(value="message", required=false, defaultValue="spring") String message) {
        model.addAttribute("message", message);
        return "index";
    }

    @RequestMapping("/login")
    public  String Login() {
        return "login";
    }

    @RequestMapping("/UserLogin")
    public String UserLogin(@RequestParam(value = "username") String username, @RequestParam String password) {
        User userModel = userDao.findUserByUsernameAndPassword(username, password);
        System.out.println(userModel.getId());
        if (userModel != null)
            return "redirect:/list";
        return "/login";
    }

    @RequestMapping("/List")
    public String List(Model model) {
        List<User> userList = userDao.findAll();
        model.addAttribute("users", userList);
        return "/list";
    }

    @GetMapping
    public ModelAndView list() {
        Message message = new Message();
        message.setCreated(Calendar.getInstance());
        message.setId(1L);
        message.setSummary("test");
        message.setText("text");
        List<Message> list = new ArrayList<Message>();
        list.add(message);
        return new ModelAndView("message/list", "messages", list);
    }

}
