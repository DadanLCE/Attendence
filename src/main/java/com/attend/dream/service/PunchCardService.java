package com.attend.dream.service;

import com.attend.dream.domain.Card;
import com.attend.dream.mapper.PunchCardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PunchCardService {

    @Autowired(required = false)
    private PunchCardMapper punchCardMapper;

    //模糊查询获取所有打卡记录
    public List<Card> getGetAllCards() {
        List<Card> cards = punchCardMapper.getCardsByCode("");
        return cards;
    }

    //通过员工编码查询他的所有打卡记录
    public List<Card> getCardAllByCode(String cardCode){

        List<Card> cards =punchCardMapper.getCardsByCode(cardCode);
        return cards;
    }

    //插入早上时间
    public Boolean insertCardMor(Card card){

        punchCardMapper.insertMorCard(card);
        return true;
    }

    // 1是有对应的插入,0是没有对应的插入
    public String insertCardEve(Card card){
        String flag = "1";
        int id;
        //获取同一个人的所有打卡记录
        List<Card> cards = punchCardMapper.getCardsByCode(card.getCardCode());

        for(Card c : cards){
            if(c.getMorTime().getDate()==card.getEveTime().getDate()) {
                id = c.getId();
                card.setId(id);
                        punchCardMapper.updateCard(card);
            }
            return "1";
        }
        punchCardMapper.insertEveCard(card);
        return "0";
    }


}

