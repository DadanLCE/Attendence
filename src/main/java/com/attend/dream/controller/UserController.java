package com.attend.dream.controller;

import com.attend.dream.domain.User;
import com.attend.dream.mapper.UserMapper;
import com.attend.dream.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/index")
    public String goToIndex(){
        return "index";
    }

    @GetMapping("/login")
    public String goToLogin(){
        return "login";
    }

    @PostMapping("/user/login")
    public String findUserByUsername(@RequestParam(value = "username" ,required = false) String username,
                                     @RequestParam(value = "password",required = false) String password,
                                     Map<String,Object> map, HttpSession session
    ) {
        System.out.println("yonhum"+username);
        if (username == null || password == null) {
            map.put("warning", "用户名或密码不能为空");
            return "login";
        } else{
            User user = userService.findUserByUsername(username);
            String pwd = user.getPassword();
            //直接判断有没有输入用户名或者密码,提示错误消息
//            if (username == null || password == null) {
//                map.put("warning", "用户名或密码不能为空");
//                return "login";
//            }
            if (pwd.equals(password)) {
                return "redirect:/index.html";
            } else {
                map.put("warning", "密码错误");
                return "login";
            }
        }

    }



}
