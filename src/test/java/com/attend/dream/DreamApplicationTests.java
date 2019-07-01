//package com.attend.dream;
//
//import com.attend.dream.domain.*;
//import com.attend.dream.mapper.*;
//import com.attend.dream.service.ClassesService;
//import com.attend.dream.service.DepartmentService;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mybatis.spring.annotation.MapperScan;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.List;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@MapperScan(basePackages = {"com.attend.dream.mapper"})
//public class DreamApplicationTests {
////    @Autowired
////    private EmployeesMapper employeesMapper;
//
//
//    @Test
//    public void contextLoads() {
//
////        Employee e = employeesMapper.getEmployeeByName("方少少");
//
////        PageHelper.startPage(2,2);
////        List<Employee> allE = employeesMapper.getEmployees();
////        PageInfo<Employee> pageInfo = new PageInfo<>(allE);
////        System.out.println(allE);
////        for ( Employee e: pageInfo
////             ) {
////            System.out.println(e);
////
////        }
////        for (Employee e:
////             allE) {
////            System.out.println(e);
////        }
//
////        System.out.println(pageInfo);
////        PageInfo<Employee> pageInfo = new PageInfo<>(allE);
//
//    }
//    @Autowired
//    private DepartmentMapper departmentMapper;
//    @Autowired
//    private DepartmentService departmentService;
//
//    @Autowired
//    private StationMapper s;
//
//    @Test
//    public void departmentT(){
//        String code = null;
//
//        Station sta = s.getStationById(11);
//        sta.setStaCode("TTA");
//        s.insertStation(sta) ;
//        System.out.println(sta);
//    }
//    @Autowired
//    private ClassesMapper classesMapper;
//    @Autowired
//    private ClassesService classesService;
//    @Test
//    public void claTest(){
////        List<Classes> list = classesService.getClassesByCode(1,3,"a");
////        PageInfo<Classes> pageInfo = classesService.getClassesByCodePage(1,3,"a");
////        Classes c = classesService.getClaById(3);
////        c.setClaCode("POSADLAKSD");
////        String a = classesService.insertCla(c);
////        System.out.println(c);
////        System.out.println(a);
////        c.setId(10);
////        String a = classesService.insertCla(c);
////        System.out.println(a);
////        System.out.println(pageInfo);
////        for (Classes c:
////             list) {
////            System.out.println(c);
////        }
////        String dateStr = "2010/05/04 12:34:23";
//        //注意format的格式要与日期String的格式相匹配
////        DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
////        try {
////            date = sdf.parse(dateStr);
////            System.out.println(date.toString());
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
////
//        String dateStr = "2015-02-10 22:00:00";
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date d1;
//        try {
//            d1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateStr);
//            System.out.println("DateTime d1>>>>>>: " + d1);
//        } catch (Exception e1) {
//            // TODO Auto-generated catch block
//            e1.printStackTrace();
//        }
////
////
////        Card card = new Card();
////        card.setDate(date);
////        System.out.println(card.getDate());
//
//    }
//
//
//
//    @Test
//    public void cardT(){
////        List<RepairCard> c = repairCardMapper.getCardsByCode("b");
//
////        Date date = null;
////        Date date2 = null;
//////        注意format的格式要与日期String的格式相匹配
////        String dateStr = "2019-08-01 00:00:00";
////        String dateStr2 = "2019-08-20 00:00:00";
////        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
////        try {
////            date = sdf.parse(dateStr);
////            date2 = sdf.parse(dateStr2);
////            System.out.println(date2.toString());
////            System.out.println(date.toString());
////
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
////
////        List<CardEve> list = cardEveMapper.getAll("",date,date2);
////        System.out.println(list);
////        for (int i =0; i < list.size();i++){
////            System.out.println(list.get(i));
////        }
////        cardMorMapper.insertCardMor(card);
////        System.out.println(list);
////        CardMor card1 = cardMorMapper.getCardMorById(2);
////        System.out.println(card1);
////        card1.setName("哇啦啦");
////        cardMorMapper.updateCard(card1);
////        CardMor cardMor = cardMorMapper.getCardMorByCode("AA");
//
//    }
//
//    public static int countTime(Date preDate, Date nextDate)
//    {
//        int days = (int) ((nextDate.getTime() - preDate.getTime()) / (1000*3600*24));
//        return days;
//    }
//
////    @Autowired
////    private  CheckCardMapper checkCardMapper;
////    @Test
////    public void rCard(){
////                Date date = null;
////        Date date2 = null;
////
//////        注意format的格式要与日期String的格式相匹配
////        String dateStr = "2019-06-26 00:00:00";
////        String dateStr2 = "2019-06-26 00:00:00";
////        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
////        try {
////            date = sdf.parse(dateStr);
////            date2 = sdf.parse(dateStr2);
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
////        List<Card> list = checkCardMapper.TestTest("W",date,date2);
////        System.out.println(list);
////    }
//    @Autowired
//    private PayMapper payMapper;
//
//    @Test
//    public void ss(){
//        Card card = checkCardMapper.getCardById(200);
//
//        Date date = null;
////        注意format的格式要与日期String的格式相匹配
//        String dateStr = "2222-08-01 00:00:00";
//        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        try {
//            date = sdf.parse(dateStr);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        card.setMorTime(date);
//        card.setNote("llllll");
//        System.out.println("date"+date);
//        System.out.println(card.getEveTime());
//        checkCardMapper.updateMorCard(card);
//    }
//
//    public void salaryAAAA(){
////        int days = countTime(preDate, nextDate);
//        String empCode = "sad";
//        int[] k = new int[12];//旷工
//        int[] d = new int[12];//迟到
//        int[] z = new int[12];//准时
//        Pay pay = new Pay();
//        List<Card> cards = checkCardMapper.getCardByCode(empCode);
//
//        Date edate = cards.get(0).getMorTime(); //最早时间
//        Date ldate = cards.get(0).getEveTime(); //最晚时间
//        int flag = edate.getMonth();
//        for(Card a : cards){
//            if(edate.compareTo(a.getMorTime())!= -1){
//                edate = a.getMorTime();
//            }
//        }
//        System.out.println(edate);
//        for(Card a : cards){
//            if(ldate.compareTo(a.getMorTime())== -1){
//                ldate = a.getMorTime();
//            }
//        }
//        System.out.println(ldate);
//
//        //flag初始值为开始计算工资的月份，结束值为最后计算工资的月份
//        //从最早月份开始遍历
//        for(int i = edate.getMonth();i <= ldate.getMonth();i++){
//                z[i]=0;k[i]=0;d[i]=0;
//            for(Card c : cards){
//                if(c.getNote()!=null) {
//                    if (c.getMorTime().getMonth() == i) {
//                        if (c.getNote().equals("旷工")) {
//                            k[flag]++;
//                        } else if (c.getNote().equals("迟到")) {
//                            d[flag]++;
//                        } else {
//                            z[flag]++;
//                        }
//                    }
//                }
//            }
//
//        }
//
//        System.out.println(z[flag]+"---"+k[flag]+"---"+d[flag]);
//
//
//    }
//
//
//}
