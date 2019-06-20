package com.attend.dream.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/employee")
    public String gotoEmp() {
        return "employee_list";
    }
}
