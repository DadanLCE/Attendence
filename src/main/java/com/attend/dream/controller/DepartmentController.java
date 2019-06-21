package com.attend.dream.controller;

import com.attend.dream.domain.Department;
import com.attend.dream.domain.Employee;
import com.attend.dream.service.DepartmentService;
import com.attend.dream.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.List;

@Controller
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;


    @GetMapping("/department")
    public String list(Model model){
        Collection<Department> departments= departmentService.getDepartments();
        model.addAttribute("deps",departments);

        return "department_list";
    }


    @PostMapping("/dep/get")
    public String getDepartmentsByDepCode(@RequestParam(value = "depCode") String depCode, Model model){
        List<Department> departments = departmentService.getDepartmentsByDepCode(depCode);
        model.addAttribute("deps",departments);
        return "department_list";
    }


}
