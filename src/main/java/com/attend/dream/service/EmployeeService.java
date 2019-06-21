package com.attend.dream.service;

import com.attend.dream.domain.Employee;
import com.attend.dream.mapper.EmployeesMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired(required = false)
    EmployeesMapper employeesMapper;

    public List<Employee> getEmployees(){
        List<Employee> list = employeesMapper.getEmployees();
        return list;
    }


}
