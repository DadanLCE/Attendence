package com.attend.dream.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {


    @RequestMapping(value = "/showEmp",method = RequestMethod.POST)
    public String showEmp(){
        return "employee_list";
    }


    @RequestMapping(value = "/showWel",method = RequestMethod.POST)
    public String showWel(){ return "welcome";}

    @RequestMapping(value= "/showDep",method = RequestMethod.POST)
    public String showDep(){ return "department_list"; }
}
