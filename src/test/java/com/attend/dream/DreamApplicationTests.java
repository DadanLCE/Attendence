package com.attend.dream;

import com.attend.dream.domain.Employee;
import com.attend.dream.mapper.EmployeesMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@MapperScan(basePackages = {"com.attend.dream.mapper"})
public class DreamApplicationTests {
    @Autowired
    private EmployeesMapper employeesMapper;


    @Test
    public void contextLoads() {
       List<Employee> list  =  employeesMapper.getEmployees();
       for(Employee e:list){
           System.out.println(e);
       }
    }

}
