package com.attend.dream.controller;


import com.attend.dream.domain.Employee;
import com.attend.dream.service.EmployeeService;
import com.attend.dream.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import sun.plugin2.message.Message;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

//    @RequestMapping("/query")
//    @ResponseBody
//    public List<Employee> getEmployees(){
//        return employeeService.getEmployees();
//    }
//
//    @RequestMapping("/hat")
//    public String Show(Model model){
//        model.addAttribute("")
//    }

    @ResponseBody
    @RequestMapping("/empList")
    public List<Employee> empList(){
        List<Employee> list = new ArrayList<>();
        list = employeeService.getEmployees();
        return list;
    }
//    @ResponseBody
//    @RequestMapping("/list")
//    public List<Employee> list(@RequestBody Message message) throws IOException{
//        List<Employee> list = new ArrayList<>();
//
//    }
}
