package com.attend.dream.controller;

import com.attend.dream.domain.Department;
import com.attend.dream.domain.Employee;
import com.attend.dream.service.DepartmentService;
import com.attend.dream.service.EmployeeService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;


    //删除单个部门
    @PostMapping("/dep/delDepById/{id}")
    @ResponseBody
    public String deleteDepartment(@PathVariable(value = "id") int id){
        System.out.println(id);
        departmentService.deleteDepartment(id);
        return "redirect:/department";
    }

    //点击添加跳转到添加页面
    @GetMapping("/dep/goToAddHtml")
    public String gotoAddDepartment() {
        return "add_department";
    }


    //查询，对应前端tbody
    @RequestMapping(value = "/department", method = RequestMethod.POST)
    @ResponseBody
    public Map<Object, Object> getDepartmentsByName(@RequestParam(value = "currentPage") int currentPage,
                                                  @RequestParam(value = "pageSize", defaultValue = "5") int pageSize,@RequestParam(value = "depCode") String depCode){
        PageInfo<Department> depsPage = departmentService.getDepartmentsByDepCodePage(currentPage,pageSize,depCode);
        List<Department> deps = departmentService.getDepartmentsByDepCode(currentPage,pageSize,depCode);
        int prePage = depsPage.getPrePage();
        int nextPage = depsPage.getNextPage();
        int pageNum = depsPage.getPages();

        Map<Object, Object> depMap = new HashMap();
        depMap.put("deps", deps);
        depMap.put("nextPage", nextPage);
        depMap.put("prePage", prePage);
        depMap.put("pageNum", pageNum);

        return depMap;
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

    //添加部门
    @PostMapping("/dep/addDepartment")
    @ResponseBody
    public String addDepartment(Department dep, Map<String,Object> map) {

        String fallBack = departmentService.insertDepartment(dep);
        if ( fallBack.equals("1")) {
            return fallBack;
        } else if ( fallBack.equals("2")){
            return fallBack;
        } else if (fallBack.equals("3")) {
            return fallBack;
        } else{
            return "error";
        }

    }

    @GetMapping("/dep/getDepById/{id}")
    @ResponseBody
    public Department getDepById(@PathVariable(value = "id") int depId) {
        //Employee emp = employeeService.getEmpById(empId);
        Department dep = departmentService.getDepById(depId);
        return dep;

    }

    @PostMapping("/dep/updateDep")
    @ResponseBody
    public String updateDepartment(Department d) {
        String fallBack = departmentService.updateDepartment(d);
        return fallBack;
    }


}
