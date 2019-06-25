package com.attend.dream;

import com.attend.dream.domain.*;
import com.attend.dream.mapper.*;
import com.attend.dream.service.ClassesService;
import com.attend.dream.service.DepartmentService;
import com.attend.dream.service.RepairCardService;
import com.attend.dream.service.StationService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    @Autowired
    private StationMapper s;

    @Test
    public void departmentT(){
        String code = null;

        Station sta = s.getStationById(11);
        sta.setStaCode("TTA");
        s.insertStation(sta) ;
        System.out.println(sta);
    }
    @Autowired
    private ClassesMapper classesMapper;
    @Autowired
    private ClassesService classesService;
    @Test
    public void claTest(){
//        List<Classes> list = classesService.getClassesByCode(1,3,"a");
//        PageInfo<Classes> pageInfo = classesService.getClassesByCodePage(1,3,"a");
//        Classes c = classesService.getClaById(3);
//        c.setClaCode("POSADLAKSD");
//        String a = classesService.insertCla(c);
//        System.out.println(c);
//        System.out.println(a);
//        c.setId(10);
//        String a = classesService.insertCla(c);
//        System.out.println(a);
//        System.out.println(pageInfo);
//        for (Classes c:
//             list) {
//            System.out.println(c);
//        }
//        String dateStr = "2010/05/04 12:34:23";
        //注意format的格式要与日期String的格式相匹配
//        DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//        try {
//            date = sdf.parse(dateStr);
//            System.out.println(date.toString());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
        String dateStr = "2015-02-10 22:00:00";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d1;
        try {
            d1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateStr);
            System.out.println("DateTime d1>>>>>>: " + d1);
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
//
//
//        Card card = new Card();
//        card.setDate(date);
//        System.out.println(card.getDate());

    }

    @Autowired
    private CardMorMapper cardMorMapper;

    @Test
    public void cardT(){

        CardMor card = cardMorMapper.getCardMorById(1);
        List<CardMor> list = cardMorMapper.getCardMorByCode("A");

        System.out.println(list);




    }



}
