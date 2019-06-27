package com.attend.dream.service;

import com.attend.dream.domain.Card;
import com.attend.dream.domain.Pay;
import com.attend.dream.mapper.CheckCardMapper;
import com.attend.dream.mapper.PayMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PayService {
    @Autowired
    private PayMapper payMapper;

    @Autowired
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
        PageHelper.startPage(currentPage, pageSize);
        List<Pay> pays = payMapper.getPaysByCode(empCode);
        return pays;
    }

    public static int countTime(Date preDate, Date nextDate)
    {
        int days = (int) ((nextDate.getTime() - preDate.getTime()) / (1000*3600*24));
        return days;
    }
    public int salary(String empCode){
        int[] k = new int[12];//旷工
        int[] d = new int[12];//迟到 早退 不正常
        int[] z = new int[12];//准时
        Pay pay = new Pay();
        List<Card> cards = checkCardMapper.getCardByCode(empCode);

        Date edate = cards.get(0).getMorTime(); //最早时间
        Date ldate = cards.get(0).getEveTime(); //最晚时间
        int aflag= edate.getMonth();
        int flag = edate.getMonth();
        for(Card a : cards){
            if(edate.compareTo(a.getMorTime())!= -1){
                edate = a.getMorTime();
            }
        }
        for(Card a : cards){
            if(ldate.compareTo(a.getMorTime())== -1){
                ldate = a.getMorTime();
            }
        }

            //aflag值为开始计算工资的月份，flag为最后计算工资的月份
            //从最早月份开始遍历
            for(int i = edate.getMonth();i <= ldate.getMonth();i++){

                    for(Card c : cards){
                        if(c.getNote()!=null) {
                            if (c.getMorTime().getMonth() == i) {
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
                flag++;
            }

            for (int i = aflag; i<flag; i++){
                int salary = d[i]*50+z[i]*100;
                pay.setStartDay(edate);
                pay.setEndDay(ldate);
                pay.setSalary(salary);
                pay.setEmpCode(empCode);
                pay.setName(cards.get(0).getName());
                payMapper.insertPay(pay);
            }

        return 0;
    }
}
