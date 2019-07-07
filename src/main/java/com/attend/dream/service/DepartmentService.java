package com.attend.dream.service;

import com.attend.dream.domain.Department;
import com.attend.dream.domain.Employee;
import com.attend.dream.domain.Station;
import com.attend.dream.mapper.DepartmentMapper;
import com.attend.dream.mapper.EmployeesMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
 * @description: 部门
 * */


@Service
public class DepartmentService {


    @Autowired(required = false)
    DepartmentMapper departmentMapper;

    @Autowired(required = false)
    EmployeesMapper employeesMapper;


    //模糊查询
    //查询页面的信息 上一页 下一页
    public PageInfo<Department> getDepartmentsByDepCodePage(int currentPage, int pageSize, String depCode){
        PageHelper.startPage(currentPage, pageSize);
        List<Department> deps = departmentMapper.getDepartmentsByDepCode(depCode);
        PageInfo<Department> pageInfo = new PageInfo<>(deps);
        return pageInfo;
    }

    //查询到的部门信息
    public List<Department> getDepartmentsByDepCode(int currentPage, int pageSize, String depCode){
        PageHelper.startPage(currentPage, pageSize);
        List<Department> deps = departmentMapper.getDepartmentsByDepCode(depCode);
        return deps;
    }

    //查询全部
    public List<Department> getDepartments(){
        List<Department> list = departmentMapper.getDepartments();
        return list;
    }

    //删除单个部门
    public boolean deleteDepartment(int depId){
        departmentMapper.deleteDepartment(depId);
        return true;
    }


    //添加新部门
    public String insertDepartment(Department d) {

        String code = d.getDepCode();

        /**
         * 判断是否已经存在部门编码
         */

        if (departmentMapper.getDepartmentByDepCode(code) != null ) {
            return "2";
            /**
             * 判断是否有这个负责人
             */
        } else if ( employeesMapper.getEmployeesByempCode(d.getDepBossCode()) == null ){
            return "3";
        } else {
           departmentMapper.insertDepartments(d);
            return "1";
        }


    }

    public Department getDepById( int depId) {
            return departmentMapper.findDepById(depId);
    }

    //更新部门
    public String updateDepartment(Department d) {
            String topID = d.getDepTopId();
            Department topDep = departmentMapper.getDepartmentByDepCode(topID);
            Employee emp = employeesMapper.getEmployeesByempCode(d.getDepBossCode());
            if ( emp == null) {
                return "2";
            } else if (topDep == null) {
                return "3";
            } else {
                departmentMapper.updateDepartment(d);
                return "1";
            }
    }

}
