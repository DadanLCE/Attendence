package com.attend.dream.controller;

import com.attend.dream.domain.Card;

import com.attend.dream.domain.Current;
import com.attend.dream.service.PunchCardService;
import com.attend.dream.service.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class PunchCardController {

    @Autowired
    private PunchCardService punchCardService;

    @Autowired
    private LoginController loginController;

    @Autowired
    private UserService userService;

    //查询功能
    @RequestMapping(value = "/pun", method = RequestMethod.POST)
    @ResponseBody
    public Map<Object, Object> getEmployeesByName(@RequestParam(value = "currentPage") int currentPage,
                                                  @RequestParam(value = "pageSize", defaultValue = "5") int pageSize, @RequestParam(value = "cardCode") String cardCode){

        List<Card> puns = punchCardService.getCardAllByCode(cardCode);
        Map<Object, Object> punMap = new HashMap();
        punMap.put("puns", puns);

        return punMap;
    }

    //打卡功能：获取当前登录用户并返回用户信息
    @RequestMapping("/getInfo")
    @ResponseBody
    public Current getInfo(){
        String name = loginController.getCurrentUser();
        String code = userService.findUserByUsername(name).getUserCode();
        Current info = new Current();
        info.setName(name);
        info.setCardCode(code);
        return info;
    }

    //添加早上打卡信息
    @RequestMapping("/pun/addPunchCardMor")
    @ResponseBody
    public void addPunchCardMor(Card card){
        punchCardService.insertCardMor(card);
    }

    //添加下午打卡信息
    @RequestMapping("/pun/addPunchCardEve")
    @ResponseBody
    public void addPunchCardEve(Card card){
       String fallback = punchCardService.insertCardEve(card);
    }



}
