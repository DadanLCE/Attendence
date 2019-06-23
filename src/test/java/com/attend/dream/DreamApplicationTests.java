package com.attend.dream;

import com.attend.dream.domain.Department;
import com.attend.dream.domain.Employee;
import com.attend.dream.mapper.DepartmentMapper;
import com.attend.dream.mapper.EmployeesMapper;
import com.attend.dream.service.DepartmentService;
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
    @Autowired
    private DepartmentMapper departmentMapper;
    @Autowired
    private DepartmentService departmentService;

    @Test
    public void departmentT(){
        String code = null;
        PageInfo<Department> pageInfo = departmentService.getDepartmentsByDepCodePage(1,5,"");
       List<Department> list =  departmentService.getDepartmentsByDepCode(1,5,"");
//        for (Department d:
//             list   ) {
//            System.out.println(d);
//        }
        System.out.println(pageInfo);
    }
}
