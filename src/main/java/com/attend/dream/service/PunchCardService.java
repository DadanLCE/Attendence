package com.attend.dream.service;

import com.attend.dream.domain.Card;
import com.attend.dream.mapper.CardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PunchCardService {

    @Autowired(required = false)
    private CardMapper cardMapper;

    public List<Card> getCardsByCode(String cardCode){
        List<Card> cards = cardMapper.getCardsByCode(cardCode);
        return cards;
    }

    public Boolean insertCard(Card card){
        cardMapper.insertCard(card);
        return true;
    }
}

