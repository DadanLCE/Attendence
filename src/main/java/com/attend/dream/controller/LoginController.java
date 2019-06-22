package com.attend.dream.controller;

import com.attend.dream.domain.User;
import com.attend.dream.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class LoginController {

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
        //System.out.println(username+"adfads");
        User user = userService.findUserByUsername(username);
        if (user == null) {
            map.put("warning","请输入正确的用户名和密码");
            return "login";
        }
        String pwd = user.getPassword();
        if ( pwd.equals(password)) {
            session.setAttribute("usersHadLogin", username);
            return "redirect:/index.html";
            //return "redirect:/index.html";
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
