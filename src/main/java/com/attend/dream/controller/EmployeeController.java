package com.attend.dream.controller;


import com.attend.dream.domain.Employee;
import com.attend.dream.service.EmployeeService;
import com.attend.dream.service.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sun.plugin2.message.Message;

import javax.servlet.http.HttpServletRequest;
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



    //通过 分页 显示全部员工列表
    @GetMapping("/employee")
    public String employeePage(Model model,@RequestParam(value = "currentPage") int currentPage,
                                @RequestParam(value = "pageSize", defaultValue = "5") int pageSize){


        PageInfo<Employee> employeePage = employeeService.getEmployeesPage(currentPage,pageSize);
        List<Employee> emps = employeeService.getEmployeesByPage(currentPage,pageSize);
        model.addAttribute("emps",emps);
        model.addAttribute("empsPage",employeePage);
        System.out.println(employeePage.getPageSize());
        System.out.println(employeePage.getNextPage());
        return "employee_list";
    }

    //查询员工通过名字 模糊查询 分页
    @RequestMapping("/emp/get")
    public String getEmployeesByName(Model model,@RequestParam(value = "currentPage") int currentPage,
                                     @RequestParam(value = "pageSize", defaultValue = "5") int pageSize,@RequestParam(value = "empName") String empName){
        PageInfo<Employee> empsPage = employeeService.getEmployeesPageMsgByName(currentPage,pageSize,empName);
        List<Employee> emps = employeeService.getEmployeesMsgByName(currentPage,pageSize,empName);
        model.addAttribute("emps",emps);
        model.addAttribute("empsPage",empsPage);
        //模糊查询判断
        model.addAttribute("empName",empName);
        model.addAttribute("isFuzzy",1);
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
        return "redirect:/employee";
    }


    //批量删除
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

        if ( fallBack.equals("1")) {
            return "redirect:/employee";
        } else if ( fallBack.equals("2")){
            map.put("msg","员工编码已存在");
            return "add_employee";
        } else if (fallBack.equals("3")) {
            map.put("msg","暂时还没有这个岗位！！！");
            return "add_employee";
        } else{
            return "index";
        }

    }
}
