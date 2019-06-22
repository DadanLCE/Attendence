package com.attend.dream.controller;

import com.attend.dream.domain.Department;
import com.attend.dream.domain.Employee;
import com.attend.dream.service.DepartmentService;
import com.attend.dream.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@Controller
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    //显示全部部门
    @GetMapping("/department")
    public String list(Model model){
        Collection<Department> departments= departmentService.getDepartments();
        model.addAttribute("deps",departments);

        return "department_list";
    }


    //根据编码查询单个部门
    @PostMapping("/dep/get")
    public String getDepartmentsByDepCode(@RequestParam(value = "depCode") String depCode, Model model){
        List<Department> departments = departmentService.getDepartmentsByDepCode(depCode);
        model.addAttribute("deps",departments);
        return "department_list";
    }

    //删除单个部门
    @DeleteMapping("/dep/{depId}")
    public String deleteEmployee(@PathVariable(value = "depId") int depId){
        departmentService.deleteDepartment(depId);
        return "redirect:/department";
    }


    //批量删除部门
    @DeleteMapping("/dep/depsDel")
    public String deleteDepartments(@RequestParam(value = "depIds") int[] depIds){
        for(int i : depIds)
            departmentService.deleteDepartment(i);
        return "redirect:/department";
    }


}
