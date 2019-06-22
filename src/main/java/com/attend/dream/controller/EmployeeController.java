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
    private int maxPage ;

    //显示全部员工列表(保留，不要删除这个！)
//    @GetMapping("/employee")
//    public String list(Model model){
//        Collection<Employee> employees=employeeService.getEmployees();
//        model.addAttribute("emps",employees);
//
//        return "employee_list";
//    }


    //通过 分页 显示全部员工列表
    @GetMapping("/employee")
    public String employeePage(Model model,@RequestParam(value = "currentPage") int currentPage,
                                @RequestParam(value = "pageSize", defaultValue = "5") int pageSize){


        PageInfo<Employee> employeePage = employeeService.getEmployeesPage(currentPage,pageSize);
        List<Employee> emps = employeeService.getEmployeesByPage(currentPage,pageSize);
        model.addAttribute("emps",emps);
        model.addAttribute("empsPage",employeePage);
        maxPage = employeePage.getNavigateLastPage();

        return "employee_list";
    }

    //查询通过姓名查询员工（模糊查询）
    @PostMapping("/emp/get")
    public String getEmployeesByName(@RequestParam(value = "empName") String empName,Model model){
        List<Employee> employee = employeeService.getEmployeesByName(empName);
        model.addAttribute("emps",employee);
        return "employee?currentPage=1";
    }


//    //点击添加跳转到添加页面
//    @GetMapping("/emp/goToAddHtml")
//    public String gotoAddEmployee() {
//        return "add_employee";
//    }

    //删除单个员工信息
    @DeleteMapping("/emp/{empId}")
    public String deleteEmployee(@PathVariable(value = "empId") int empId){
        employeeService.deleteEmployee(empId);
        return "redirect:/employee?currentPage=1";
    }


    //批量删除员工
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
            return "redirect:/employee?currentPage="+maxPage;
        } else if ( fallBack.equals("2")){
            map.put("msg","员工编码已存在");
            map.put("addWarningMsg","2");
            return "add_employee";
        } else if (fallBack.equals("3")) {
            map.put("msg","暂时还没有这个岗位！！！");
            map.put("addWarningMsg","3");
            return "add_employee";
        } else{
            return "index";
        }



    }

//      请勿删除
//    @GetMapping("/emp/goToUpdateHtml")
//    public String gotoUpdateEmployee() {
//        return "update_employee";
//    }

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
        return "redirect:/employee?currentPage=1";
    }

}
