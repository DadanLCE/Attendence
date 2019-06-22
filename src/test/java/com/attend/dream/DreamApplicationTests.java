package com.attend.dream;

import com.attend.dream.domain.Employee;
import com.attend.dream.mapper.EmployeesMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
//    @Autowired
//    private EmployeesMapper employeesMapper;


    @Test
    public void contextLoads() {
//        Employee e = employeesMapper.getEmployeeByName("方少少");

//        PageHelper.startPage(2,2);
//        List<Employee> allE = employeesMapper.getEmployees();
//        PageInfo<Employee> pageInfo = new PageInfo<>(allE);
//        System.out.println(allE);
//        for ( Employee e: pageInfo
//             ) {
//            System.out.println(e);
//
//        }
//        for (Employee e:
//             allE) {
//            System.out.println(e);
//        }

//        System.out.println(pageInfo);
//        PageInfo<Employee> pageInfo = new PageInfo<>(allE);

    }

}
