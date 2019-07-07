package com.attend.dream.controller;

import com.attend.dream.domain.Card;
import com.attend.dream.domain.CardEchar;
import com.attend.dream.service.PunchCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class WelcomeController {
    @Autowired
    private PunchCardService punchCardService;

    @RequestMapping(value = "/getEchar", method = RequestMethod.POST)
    @ResponseBody
    public List<CardEchar> getPerson(){
        List<CardEchar> list = punchCardService.getNum();
        System.out.println(list);
        return list;
    }
}
