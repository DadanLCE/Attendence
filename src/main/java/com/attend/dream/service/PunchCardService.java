package com.attend.dream.service;

import com.attend.dream.domain.Card;
import com.attend.dream.domain.CardEchar;
import com.attend.dream.mapper.PunchCardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PunchCardService {

    @Autowired(required = false)
    private PunchCardMapper punchCardMapper;



    public List<Card> getGetAllCards() {
        List<Card> cards = punchCardMapper.getCardsByCode("");
        return cards;
    }
    public List<Card> getCardAllByCode(String cardCode){

        List<Card> cards =punchCardMapper.getCardsByCode(cardCode);
        return cards;
    }

    public Boolean insertCardMor(Card card){

        punchCardMapper.insertMorCard(card);
        return true;
    }

    // 1是有对应的插入,0是没有对应的插入
    public String insertCardEve(Card card){
        System.out.println("````````````````````````````");
        String flag = "1";
        int id;
        //获取同一个人的所有打卡记录
        List<Card> cards = punchCardMapper.getCardsByCode(card.getCardCode());
        System.out.println(card.getEveTime().getDate());

        for(Card c : cards){
            if(c.getMorTime()!=null) {
                if(c.getMorTime().getMonth() == card.getEveTime().getMonth()) {
                    if (c.getMorTime().getDate() == card.getEveTime().getDate()) {
                        id = c.getId();
                        card.setId(id);
                        punchCardMapper.updateCard(card);
                        return "1";
                    }
                }
            }
        }
        System.out.println("***********");
        punchCardMapper.insertEveCard(card);
        return "0";
    }

    //Echar 获取用户的打卡数
    public List<CardEchar> getPunchCard(){
        List<CardEchar> cardNumList = new ArrayList<>();
        List<Card> cardList = punchCardMapper.cardsEchart();
        for(int i=0; i<cardList.size(); i++){
            CardEchar cardEchar = new CardEchar();
            Card card = cardList.get(i);
            int num = punchCardMapper.cardsNumEchart(card.getName());
            cardEchar.setName(card.getName());
            cardEchar.setNum(num);
            cardNumList.add(cardEchar);
        }
        return cardNumList;
    }



}

