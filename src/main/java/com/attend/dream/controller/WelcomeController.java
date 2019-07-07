package com.attend.dream.controller;

import com.attend.dream.domain.Card;
import com.attend.dream.domain.CardEchar;
import com.attend.dream.service.EmployeeService;
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
    @Autowired
    private EmployeeService employeeService;

    //Echar 获取用户的打卡数
    @RequestMapping(value = "/getPunchCard", method = RequestMethod.POST)
    @ResponseBody
    public List<CardEchar> getPunchCard(){
        List<CardEchar> list = punchCardService.getPunchCard();
        return list;
    }

    //获取每个岗位的人数
    @RequestMapping(value = "/getEmployeeByStation", method = RequestMethod.POST)
    @ResponseBody
    public int[] getEmployeeByStation(){
        int num[] = employeeService.getEmployeeByStation();
        return num;
    }

}
