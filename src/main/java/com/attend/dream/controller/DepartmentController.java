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
import java.util.Map;

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

    //点击添加跳转到添加页面
    @GetMapping("/dep/goToAddHtml")
    public String gotoAddDepartment() {
        return "add_department";
    }

    //批量删除
    @PostMapping("/dep/depsDel")
    public String depsDelete(String depList){
        String[] strs = depList.split(",");
        for (int i = 0; i < strs.length; i++) {
            departmentService.deleteDepartment(Integer.parseInt(strs[i]));
        }
        return "department_list";

    }

    //添加员工
    @PostMapping("/dep/addDepartment")
    public String addDepartment(Department dep, Map<String,Object> map) {

        String fallBack = departmentService.insertDepartment(dep);

        if ( fallBack.equals("1")) {
            return "redirect:/department";
        } else if ( fallBack.equals("2")){
            map.put("msg","部门已存在");
            return "add_department";
        } else if (fallBack.equals("3")) {
            map.put("msg","负责人不存在");
            return "add_department";
        } else{
            return "index";
        }

    }


}
