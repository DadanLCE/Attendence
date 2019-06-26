package com.attend.dream.service;

import com.attend.dream.domain.Card;
import com.attend.dream.mapper.PunchCardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PunchCardService {

    @Autowired(required = false)
    PunchCardMapper punchCardMapper;
    public List<Card> getGetAllCards() {

        List<Card> cards = punchCardMapper.getCardsByCode("");
        return cards;
    }

}
