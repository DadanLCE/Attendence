package com.attend.dream.service;

import com.attend.dream.domain.Card;
import com.attend.dream.domain.Pay;
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
    public int salary(Card card){
        days = countTime(card.get)
        return 0;
    }
}
