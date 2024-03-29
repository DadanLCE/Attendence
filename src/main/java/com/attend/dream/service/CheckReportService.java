package com.attend.dream.service;

import com.attend.dream.domain.Card;
import com.attend.dream.domain.Employee;
import com.attend.dream.mapper.CheckCardMapper;
import com.attend.dream.mapper.PunchCardMapper;
import com.attend.dream.mapper.RepairCardMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/*
 * @description: 考勤
 * */

@Service
public class CheckReportService {

    @Autowired(required = false)
    PunchCardMapper punchCardMapper;
    @Autowired(required = false)
    RepairCardMapper repairCardMapper;
    @Autowired(required = false)
    CheckCardMapper checkCardMapper;


    //获取特定员工日期的打卡单
    public List<Card> getSpecialRecord(String cardCode, Date preDate,Date nextDate) {
        List<Card> specialRecord = punchCardMapper.getCardsByCodeTime(cardCode,preDate,nextDate);
        return specialRecord;
    }

    //插入打卡单到考勤表
    public int insetAllDayTime(Card card) {
        return checkCardMapper.insertAllDayCard(card);
    }

    //更新早上打卡时间
    public boolean updateMorCard(Card card) {
        checkCardMapper.updateMorCard(card);
        return true;
    }

    //更新下午打卡时间
    public boolean updateEveCard(Card card) {
        checkCardMapper.updateEveCard(card);
        return true;
    }

    //清空考勤表
    public void delete(){
        checkCardMapper.delete();
    }

    //查询所有
    public List<Card> getAll(String cardCode,Date preDate,Date nextDate){
            return checkCardMapper.search(cardCode,preDate,nextDate);
    }

    //模糊查询 分页查询的页面数据
    public List<Card> getCardsByCodeTime(int currentPage, int pageSize, String cardCode,Date preDate,Date nextDate){
        PageHelper.startPage(currentPage,pageSize);
        List<Card> cards = checkCardMapper.getCardsByCodeTime(cardCode, preDate, nextDate);
        return cards;
    }

    //分页显示
    public PageInfo<Card> getCardsByCodeTimePage(int currentPage, int pageSize, String cardCode,Date preDate,Date nextDate){
        PageHelper.startPage(currentPage,pageSize);
        List<Card> cards = checkCardMapper.getCardsByCodeTime(cardCode, preDate, nextDate);
        PageInfo<Card> pageInfo = new PageInfo<>(cards);
        return pageInfo;
    }


    /*
    *   暂无使用
    * */

    public int insetMorTime(Card card) {
        return punchCardMapper.insertMorCard(card);
    }

    public int insetEveTime(Card card) {
        return punchCardMapper.insertEveCard(card);
    }

    public boolean updatepunCard(Card card) {
        punchCardMapper.updateCard(card);
        return true;
    }

    public boolean updateCard(Card card) {
        checkCardMapper.updateCard(card);
        return true;
    }

    public List<Map> checkCardSum() {
        List<Map> checkSum = repairCardMapper.checkCardSum();
        return checkSum;
    }


}
