package com.attend.dream.service;

import com.attend.dream.domain.Card;
import com.attend.dream.domain.Pay;
import com.attend.dream.mapper.CheckCardMapper;
import com.attend.dream.mapper.PayMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.util.calendar.Gregorian;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class PayService {
    @Autowired(required = false)
    private PayMapper payMapper;

    @Autowired(required = false)
    private CheckCardMapper checkCardMapper;
    //模糊查询
    //查询页面的信息 上一页 下一页
    public PageInfo<Pay> getPaysByEmpCodePage(int currentPage, int pageSize, String empCode){
        PageHelper.startPage(currentPage, pageSize);
        List<Pay> pays = payMapper.getPaysByCode(empCode);
        PageInfo<Pay> pageInfo = new PageInfo<>(pays);
        return pageInfo;
    }
    //查询到的部门信息
    public List<Pay> getPaysByEmpCode(int currentPage, int pageSize, String empCode){
        payMapper.delete();
        List<Pay> pays = new ArrayList<>();
        if (empCode != "") {
            salary(empCode);
            PageHelper.startPage(currentPage, pageSize);
            pays = payMapper.getPaysByCode(empCode);
        }else{
            getAll();
            PageHelper.startPage(currentPage, pageSize);
            pays = payMapper.getPaysByCode(empCode);
        }
        return pays;
    }

    public static int countTime(Date preDate, Date nextDate)
    {
        int days = (int) ((nextDate.getTime() - preDate.getTime()) / (1000*3600*24));
        return days;
    }

    public void salary(String empCode){
        int[] k = new int[13];//旷工
        int[] d = new int[13];//迟到 早退 不正常
        int[] z = new int[13];//准时
        Pay pay = new Pay();
        List<Card> cards = checkCardMapper.getCardByCode(empCode);
        if(cards.get(0)!=null ) {
            Date edate = cards.get(0).getMorTime(); //最早时间
            Date ldate = cards.get(0).getEveTime(); //最晚时间
            int aflag = edate.getMonth();
            int flag = edate.getMonth();
            for (Card a : cards) {
                if(a.getMorTime()!=null){
                    if (edate.compareTo(a.getMorTime()) != -1) {
                        edate = a.getMorTime();
                    }
                }
            }
            for (Card a : cards) {
                if(a.getMorTime()!=null){
                    if (ldate.compareTo(a.getMorTime()) == -1) {
                        ldate = a.getMorTime();
                    }
                }
            }
            //aflag值为开始计算工资的月份，flag为最后计算工资的月份
            //从最早月份开始遍历
            for (int i = edate.getMonth(); i <= ldate.getMonth(); i++) {

                for (Card c : cards) {
                    if (c.getNote() != null) {
                        if (c.getMorTime() != null ){
                            if (c.getMorTime().getMonth() == i) {
                                if (c.getNote().equals("旷工")) {
                                    k[flag]++;
                                } else if (c.getNote().equals("不正常")) {
                                    d[flag]++;
                                } else {
                                    z[flag]++;
                                }
                            }
                        }else if(c.getEveTime()!=null){
                            if (c.getEveTime().getMonth() == i) {
                                if (c.getNote().equals("旷工")) {
                                    k[flag]++;
                                } else if (c.getNote().equals("不正常")) {
                                    d[flag]++;
                                } else {
                                    z[flag]++;
                                }
                            }
                        }
                    }
                }
                flag++;
            }

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
            GregorianCalendar gc = new GregorianCalendar();
            Calendar cal = Calendar.getInstance();
            int salary = d[aflag] * 50 + z[aflag] * 100;
            pay.setStartDay(edate);
            String y = sdf.format(edate);
            int ye = Integer.parseInt(y);
            gc.set(Calendar.YEAR, ye);

            gc.set(Calendar.MONTH, aflag);
            int ts = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
            gc.set(Calendar.DAY_OF_MONTH, ts);
            Date lastDay = gc.getTime();
            pay.setEndDay(lastDay);//第一个月的最后一天
            pay.setSalary(salary);
            pay.setEmpCode(empCode);
            pay.setName(cards.get(0).getName());
            payMapper.insertPay(pay);
            if (aflag != flag) {
                aflag++;
                for (int i = aflag; i < flag; i++) {

                    salary = d[aflag] * 50 + z[aflag] * 100;
                    gc.set(Calendar.MONTH, aflag);
                    ts = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
                    gc.set(Calendar.DAY_OF_MONTH, 1);
                    edate = gc.getTime();
                    pay.setStartDay(edate);
                    gc.set(Calendar.DAY_OF_MONTH, ts);
                    lastDay = gc.getTime();
                    pay.setEndDay(lastDay);//第一个月的最后一天
                    pay.setSalary(salary);
                    pay.setEmpCode(cards.get(0).getCardCode());
                    pay.setName(cards.get(0).getName());
                    payMapper.insertPay(pay);
                }
            }
        }
    }

    public void getAll(){
        int flag = 1;
        List<String> codes = new ArrayList<>();
        List<Card> cards = new ArrayList<>();
        cards = checkCardMapper.getCardsByCode("");
        //判断编码是否已经在cards序列里
        for(Card card : cards){ //遍历出勤记录
            String empc = card.getCardCode();
            for(String code : codes){//判断序列中是否有该编码
                if(code.equals(empc)){
                    flag=0;
                }
            }
            if(flag == 1){
                codes.add(card.getCardCode());
            }
            flag = 1;
        }
        System.out.println(codes);
        for(String code : codes){
            salary(code);
        }
    }

    public static int getMaxDay(int year,int month){
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR,year);
        cal.set(Calendar.MONTH,month-1);
        return  cal.getActualMaximum(Calendar.DAY_OF_MONTH);
    }
}
