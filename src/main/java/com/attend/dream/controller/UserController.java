package com.attend.dream.controller;

import com.attend.dream.domain.User;
import com.attend.dream.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    public String findUserByUsername(@RequestParam(value = "username") String username,
                                     @RequestParam(value = "password") String password,
                                     Map<String,Object> map, HttpSession session
                                     ) {

        User user = userService.findUserByUsername(username);
        String pwd = user.getPassword();
        if ( pwd.equals(password)) {
            return "redirect:/index.html";
        } else {
            map.put("warning","密码错误");
            return "login";
        }

    }
//
//    @RequestMapping(value = "logout",method = RequestMethod.GET)
//    public String logout(HttpSession session){
//        session.invalidate();
//        return "logout";
//    }


}
