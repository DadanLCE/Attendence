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

        public List<Department> getDepartmentsByDepCode(String depCode){

            List<Department> department = departmentMapper.getDepartmentsByDepCode(depCode);
            return department;
        }





}
