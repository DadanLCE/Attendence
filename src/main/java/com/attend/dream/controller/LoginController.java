package com.attend.dream.controller;

import com.attend.dream.domain.User;
import com.attend.dream.mapper.UserMapper;
import com.attend.dream.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

//登录
@Controller
public class LoginController {

    @Autowired
    UserService userService;

    //当前登录的用户名
    private String currentUser;

    //获取登录页面
    @GetMapping("/")
    public String indexControl() { return "login"; }

    //获取用户名方法
    public String getCurrentUser() {
        return currentUser;
    }

    //用户名赋值方法
    public void setCurrentUser(String currentUser) {
        this.currentUser = currentUser;
    }

    //跳转到主页
    @GetMapping("/index")
    public String goToIndex(){
        return "index";
    }

    //跳转回登录页面
    @GetMapping("/login")
    public String goToLogin(){
        return "login";
    }

    //登录验证
    @PostMapping("/user/login")
    public String findUserByUsername(@RequestParam(value = "username") String username,
                                     @RequestParam(value = "password") String password,
                                     Map<String,Object> map, HttpSession session
                                     ) {
        User user = userService.findUserByUsername(username);
        if (user == null) {
            map.put("msg","没有这个用户存在");
            return "login";
        }
        String pwd = user.getPassword();
        if ( pwd.equals(password)) {
            session.setAttribute("userLogined", username);
            setCurrentUser(username);
            map.put("user",username);
            return "index";
        } else {
            map.put("msg","对不起，用户名或者密码错误");
            return "login";
        }

    }

    //获取存在session中的用户
    @GetMapping("/user/getSession")
    @ResponseBody
    public Object getUser(HttpSession session) {
        Object u = session.getAttribute("userLogined");
        System.out.println(u);
        return u;

    }

    //用户登录注销
    @GetMapping("/user/logout")
    public String logout(HttpSession session) {

        if ( session.getAttribute("userLogined") == null ) {
            return "login";
        } else {
            session.invalidate();
            return "login";
        }

    }


}
