package com.attend.dream.service;

import com.attend.dream.domain.Employee;
import com.attend.dream.domain.Station;
import com.attend.dream.mapper.EmployeesMapper;
import com.attend.dream.mapper.StationMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired(required = false)
    EmployeesMapper employeesMapper;

    @Autowired(required = false)
    StationMapper stationMapper;

    //分页查询的页
    public PageInfo<Employee> getEmployeesPage(int currentPage,int pageSize){
        PageHelper.startPage(currentPage,pageSize);
        List<Employee> AllEmp = employeesMapper.getEmployees();
        PageInfo<Employee> pageInfo = new PageInfo<>(AllEmp);
        return pageInfo;
    }

    //分页查询的查询数据
    public List<Employee> getEmployeesByPage(int currentPage,int pageSize){

        PageHelper.startPage(currentPage,pageSize);
        List<Employee> AllEmp = employeesMapper.getEmployees();
        return AllEmp;
    }


    public List<Employee> getEmployees(){
        List<Employee> list = employeesMapper.getEmployees();
        return list;
    }

    public List<Employee> getEmployeesByName(String empName){
        List<Employee> employee = employeesMapper.getEmployeesByName(empName);
        return employee;
    }

    //添加员工
    public String insertEmployee(Employee e) {

        //唯一编码
        String uniqueCode = e.getEmpCode();
        //获取想插入的岗位码
        String staCode = e.getEmpStaCode();

        Station sta = stationMapper.getStationByStaCode(staCode);
        Employee employee = employeesMapper.isEmpCodeExist(uniqueCode);

        /**
         *  employee != null → 存在员工，编码重复
         *  sta != null → 岗位不符合要求
         */

        if (employee != null ) {
            return "2";
        } else if (sta == null ){
            return "3";
        } else {
            employeesMapper.insertEmployee(e);
            return "1";
        }


    }

    //

    public boolean deleteEmployee(int empId){
        employeesMapper.deleteEmployee(empId);
        return true;
    }


}
