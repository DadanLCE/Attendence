package com.attend.dream.service;


import com.attend.dream.domain.RepairCard;
import com.attend.dream.mapper.RepairCardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RepairCardService {

    @Autowired(required = false)
    RepairCardMapper repairCardMapper;

    //根据编码查询补卡单
    public List<RepairCard> getCardsByCode(String cardCode){
        return repairCardMapper.getCardsByCode(cardCode);
    }




}
