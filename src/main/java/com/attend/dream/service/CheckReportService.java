package com.attend.dream.service;

import com.attend.dream.domain.Card;
import com.attend.dream.mapper.CheckCardMapper;
import com.attend.dream.mapper.PunchCardMapper;
import com.attend.dream.mapper.RepairCardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class CheckReportService {

    @Autowired(required = false)
    PunchCardMapper punchCardMapper;
    @Autowired(required = false)
    RepairCardMapper repairCardMapper;
    @Autowired(required = false)
    CheckCardMapper checkCardMapper;

    public List<Map> checkCardSum() {
        List<Map> checkSum = repairCardMapper.checkCardSum();
        return checkSum;
    }

    public List<Card> getSpecialRecord(String cardCode, Date preDate,Date nextDate) {
        List<Card> specialRecord = punchCardMapper.getCardsByCodeTime(cardCode,preDate,nextDate);
        return specialRecord;
    }

    public Card getCardBycardCode(String cardCode) {
        return punchCardMapper.getCardByCardCode(cardCode);
    }

    public int insetMorTime(Card card) {
        return checkCardMapper.insertMorCard(card);
    }

    public int insetEveTime(Card card) {
        return checkCardMapper.insertEveCard(card);
    }

    public int insetAllDayTime(Card card) {
        return checkCardMapper.insertAllDayCard(card);
    }
    public boolean updateCard(Card card) {
        checkCardMapper.updateCard(card);
        return true;
    }

    public boolean updateMorCard(Card card) {
        checkCardMapper.updateMorCard(card);
        return true;
    }

    public boolean updateEveCard(Card card) {
        checkCardMapper.updateEveCard(card);
        return true;
    }
}
