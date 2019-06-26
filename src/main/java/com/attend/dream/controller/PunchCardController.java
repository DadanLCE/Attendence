package com.attend.dream.controller;

import com.attend.dream.domain.Card;
import com.attend.dream.service.PunchCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class PunchCardController {

    @Autowired
    PunchCardService punchCardService;

    @GetMapping("/get/getCards")
    @ResponseBody
    public List<Card> getCrads() {
        List<Card> cards = punchCardService.getGetAllCards();
        return cards;
    }

}
