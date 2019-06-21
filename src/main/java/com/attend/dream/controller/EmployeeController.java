package com.attend.dream.controller;


import com.attend.dream.domain.Employee;
import com.attend.dream.service.EmployeeService;
import com.attend.dream.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sun.plugin2.message.Message;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/employee")
    public String list(Model model){
        Collection<Employee> employees=employeeService.getEmployees();
        model.addAttribute("emps",employees);

        return "employee_list";
    }


    @PostMapping("/emp/get")
    public String getEmployeesByName(@RequestParam(value = "empName") String empName,Model model){
        Employee employee = employeeService.getEmployeesByName(empName);
        model.addAttribute("emps",employee);
        return "/employee_list";
    }


}
