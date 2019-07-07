package com.attend.dream.controller;


import com.attend.dream.domain.Employee;
import com.attend.dream.service.EmployeeService;
import com.attend.dream.service.UserService;
import com.github.pagehelper.PageInfo;
import org.omg.CORBA.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sun.plugin2.message.Message;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.Response;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;
    private int maxPage ;

    //查询，对应前端tbody
    @RequestMapping(value = "/emp", method = RequestMethod.POST)
    @ResponseBody
    public Map<Object, Object> getEmployeesByName(@RequestParam(value = "currentPage") int currentPage,
                                                  @RequestParam(value = "pageSize", defaultValue = "5") int pageSize,@RequestParam(value = "empName") String empName){
        PageInfo<Employee> empsPage = employeeService.getEmployeesPageMsgByName(currentPage,pageSize,empName);
        List<Employee> emps = employeeService.getEmployeesMsgByName(currentPage,pageSize,empName);
        int prePage = empsPage.getPrePage();
        int nextPage = empsPage.getNextPage();
        int pageNum = empsPage.getPages();

        Map<Object, Object> empMap = new HashMap();
        empMap.put("emps", emps);
        empMap.put("nextPage", nextPage);
        empMap.put("prePage", prePage);
        empMap.put("pageNum", pageNum);

        return empMap;
    }

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

    //查询员工通过名字 模糊查询 分页
    @RequestMapping("/emp/get")
    public String getEmployeesByName(Model model,@RequestParam(value = "currentPage") int currentPage,
                                     @RequestParam(value = "pageSize", defaultValue = "5") int pageSize,@RequestParam(value = "empName") String empName){
        PageInfo<Employee> empsPage = employeeService.getEmployeesPageMsgByName(currentPage,pageSize,empName);
        List<Employee> emps = employeeService.getEmployeesMsgByName(currentPage,pageSize,empName);
        model.addAttribute("emps",emps);
        model.addAttribute("empsPage",empsPage);
        //模糊查询判断
        //将模糊查询的empName传入到页面 让上一页和下一页可以获取到empName
        model.addAttribute("empName",empName);
        model.addAttribute("isFuzzy",1);
        return "employee_list";
    }

    //删除单行员工信息
    @PostMapping("/emp/delEmpById/{id}")
    public String deleteEmployee(@PathVariable(value = "id") int empId){
        employeeService.deleteEmployee(empId);
        return "redirect:/employee?currentPage=1";
    }

    //
    @GetMapping("/emp/getEmpById/{id}")
    @ResponseBody
    public Employee getEmpById(@PathVariable(value = "id") int empId) {
        Employee emp = employeeService.getEmpById(empId);
        return emp;

    }

    //批量删除员工
    @PostMapping("/emp/delEmps")
    public String empsDelete(String userList){
        String[] strs = userList.split(",");
        for (int i = 0; i < strs.length; i++) {
            employeeService.deleteEmployee(Integer.parseInt(strs[i]));
        }
        return "redirect:/employee?currentPage=1";

    }

    //添加员工
    @PostMapping("/emp/addEmployee")
    @ResponseBody
    public String addEmployee(Employee emp, Map<String,Object> map) {

        String fallBack = employeeService.insertEmployee(emp);

        //也可以直接return fallBack;
        if ( fallBack.equals("1")) {
            //return "redirect:/employee?currentPage="+maxPage;
            return fallBack;
        } else if ( fallBack.equals("2")){
            map.put("msg","员工编码已存在");
            map.put("addWarningMsg","2");
            //return "add_employee";
            return fallBack;
        } else if (fallBack.equals("3")) {
            map.put("msg","暂时还没有这个岗位！！！");
            map.put("addWarningMsg","3");
            //return "add_employee";
            return fallBack;
        } else{
            return "error";
        }

    }

    @PostMapping("/emp/updateEmp")
    @ResponseBody
    public String updateEmployee(Employee e) {
        String fallBack = employeeService.updateEmployee(e);
        return fallBack;
    }

    //在部门添加负责人的时，从数据库获取，设置下拉框进行选择，避免错误操作
    @GetMapping("/emp/forCombox")
    @ResponseBody
    public List<Employee> getAll() {
        List<Employee> emps = employeeService.getEmployees();
        return emps;
    }

}
