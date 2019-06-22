package com.attend.dream.controller;


import com.attend.dream.domain.Employee;
import com.attend.dream.service.EmployeeService;
import com.attend.dream.service.UserService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.apache.ibatis.annotations.Delete;
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
import java.util.Map;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    //显示全部员工列表
    @GetMapping("/employee")
    public String list(Model model){
        Collection<Employee> employees=employeeService.getEmployees();
        model.addAttribute("emps",employees);

        return "employee_list";
    }

    //查询员工
    @PostMapping("/emp/get")
    public String getEmployeesByName(@RequestParam(value = "empName") String empName,Model model){
        List<Employee> employee = employeeService.getEmployeesByName(empName);
        model.addAttribute("emps",employee);
        return "employee_list";
    }

    @GetMapping("/emp/goToAddHtml")
    public String gotoAddEmployee() {
        return "add_employee";
    }

    //删除单行员工信息
    @DeleteMapping("/emp/{empId}")
    public String deleteEmployee(@PathVariable(value = "empId") int empId){
        employeeService.deleteEmployee(empId);
        return "redirect:/employee";
    }

    //批量删除员工
    @DeleteMapping("/emp/empsDel")
    public String deleteEmployees(@RequestParam(value = "empIds") int[] empIds){
        for(int i : empIds)
        employeeService.deleteEmployee(i);
        return "redirect:/employee";
    }


    //添加员工
    @PostMapping("/emp/addEmployee")
    public String addEmployee(Employee emp, Map<String,Object> map) {
        String empCode = emp.getEmpCode();
        employeeService.insertEmployee(emp);
        return "index";

    }



}
