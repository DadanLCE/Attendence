package com.attend.dream.service;


import com.attend.dream.domain.RepairCard;
import com.attend.dream.domain.User;
import com.attend.dream.mapper.RepairCardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RepairCardService {

    @Autowired(required = false)
    RepairCardMapper repairCardMapper;

    @Autowired
    UserService userService;

    //根据编码查询补卡单
    public List<RepairCard> getCardsByCode(String cardCode){
        return repairCardMapper.getCardsByCode(cardCode);
    }

    //添加补卡
    public String inserRepairCard(RepairCard repairCard){
        User user = userService.findUserByUsername(repairCard.getName());
        //查询这个用户名存不存在
        if(user==null){
            return "2";
        }else{
            repairCard.setCardCode(user.getUserCode());
        }
        repairCardMapper.insertAll(repairCard);
        return "1";
    }

    //删除单个补卡记录
    public void deleteRepairCard(int id){
        repairCardMapper.deleteCard(id);
    }



}
