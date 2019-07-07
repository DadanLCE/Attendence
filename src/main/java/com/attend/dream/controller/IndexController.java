package com.attend.dream.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


//点击各个具体模块，模块内容填充到index页面
@Controller
public class IndexController {


    @RequestMapping(value = "/showEmp",method = RequestMethod.POST)
    public String showEmp(){
        return "employee_list";
    }

    //!!!修改
    @RequestMapping(value = "/showWel",method = RequestMethod.POST)
    public String showWel(){ return "welcome";}

    @RequestMapping(value= "/showDep",method = RequestMethod.POST)
    public String showDep(){ return "department_list"; }

    @RequestMapping(value = "/showSta",method = RequestMethod.POST)
    public String showSta(){
        return "station_list";
    }

    @RequestMapping(value = "/showCla",method = RequestMethod.POST)
    public String showCla(){ return "classes_list"; }

    @RequestMapping(value = "/showPun",method = RequestMethod.POST)
    public String showPun(){ return "punchCard_list"; }

    @RequestMapping(value = "/showRep",method = RequestMethod.POST)
    public String showRep(){ return "repairCard_list"; }

    @RequestMapping(value = "/showPay",method = RequestMethod.POST)
    public String showPay(){ return "paySalary"; }

    @RequestMapping(value = "/showChec",method = RequestMethod.POST)
    public String showChec(){ return "checkReport_list"; }



}
