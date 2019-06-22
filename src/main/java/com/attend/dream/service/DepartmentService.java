package com.attend.dream.service;

import com.attend.dream.domain.Department;
import com.attend.dream.domain.Employee;
import com.attend.dream.mapper.DepartmentMapper;
import com.attend.dream.mapper.EmployeesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {



        @Autowired(required = false)
        DepartmentMapper departmentMapper;
        //查询全部
        public List<Department> getDepartments(){
            List<Department> list = departmentMapper.getDepartments();
            return list;
        }

        //编码查询单个部门
        public List<Department> getDepartmentsByDepCode(String depCode){

            List<Department> department = departmentMapper.getDepartmentsByDepCode(depCode);
            return department;
        }

        //删除单个部门
        public boolean deleteDepartment(int depId){
            departmentMapper.deleteDepartment(depId);
            return true;
        }






}
