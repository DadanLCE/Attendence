package com.attend.dream.service;

import com.attend.dream.domain.Card;
import com.attend.dream.mapper.RepairCardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CheckCardService {


    @Autowired
    PunchCardService punchCardService;
    @Autowired(required = false)
    RepairCardMapper repairCardMapper;

    public List<Map> getEveryDayCard() {

        List<Map> cards = repairCardMapper.checkCardSum();
        return  cards;
    }


}
