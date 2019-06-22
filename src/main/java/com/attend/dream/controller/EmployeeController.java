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

    //点击添加跳转到添加页面
    @GetMapping("/emp/goToAddHtml")
    public String gotoAddEmployee() {
        return "add_employee";
    }

    //删除单行员工信息
    @DeleteMapping("/emp/{empId}")
    public String deleteEmployee(@PathVariable(value = "empId") int empId){
        employeeService.deleteEmployee(empId);
        return "employee_list";
    }

    @PostMapping("/emp/delEmps")
    public String empsDelete(String userList){
        String[] strs = userList.split(",");
        for (int i = 0; i < strs.length; i++) {
            employeeService.deleteEmployee(Integer.parseInt(strs[i]));
        }
        return "employee_list";

    }


    //添加员工
    @PostMapping("/emp/addEmployee")
    public String addEmployee(Employee emp, Map<String,Object> map) {

        String fallBack = employeeService.insertEmployee(emp);

        if ( fallBack.equals("1") ) {
            map.put("msg","插入成功");
            return "redirect:/employee_list.html";
        } else if ( fallBack.equals("2") ){
            map.put("msg","员工编码已存在");
            return "add_employee";
        } else if ( fallBack.equals("3") ) {
            map.put("msg","暂时还没有这个岗位！！！");
            return "add_employee";
        } else{
            return "index";
        }

        //return "index";

    }

    @PostMapping("/emp/getAtagValue")
    public String getIdByAtag() {

        return "";
    }
    @GetMapping("/emp/goToUpdateHtml")
    public String gotoUpdateEmployee() {
        return "update_employee";
    }

    @GetMapping("/emp/getEmpById/{id}")
    @ResponseBody
    public Employee getEmpById(@PathVariable(value = "id") int empId) {
        Employee emp = employeeService.getEmpById(empId);
        return emp;

    }

    @PostMapping("/emp/updateEmp")
    public String updateEmployee(Employee e) {
        System.out.println(e.getEmpCode());
        employeeService.updateEmployee(e);
        return "employee_list";
    }

}
