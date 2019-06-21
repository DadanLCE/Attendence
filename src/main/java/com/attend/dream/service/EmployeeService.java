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

    public List<Employee> getEmployeesByName(String empName){
        List<Employee> employee = employeesMapper.getEmployeesByname(empName);
        return employee;
    }

    public boolean deleteEmployee(int empId){
        employeesMapper.deleteEmployee(empId);
        return true;
    }

    public boolean deleteEmployees(List<Employee> employees){
        for(Employee e : employees){
            employeesMapper.deleteEmployee(e.getEmpId());
        }
        return true;
    }
}
