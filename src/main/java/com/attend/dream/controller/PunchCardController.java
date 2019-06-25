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
    //查询，对应前端tbody
    @RequestMapping(value = "/pun", method = RequestMethod.POST)
    @ResponseBody
    public Map<Object, Object> getEmployeesByName(@RequestParam(value = "currentPage") int currentPage,
                                                  @RequestParam(value = "pageSize", defaultValue = "5") int pageSize, @RequestParam(value = "cardCode") String cardCode){
       // PageInfo<Card> punsPage = punchCardService.employeeService.getEmployeesPageMsgByName(currentPage,pageSize,cardCode));
        List<Card> puns = punchCardService.getCardsByCode(cardCode);
//        int prePage = punsPage.getPrePage();
//        int nextPage = punsPage.getNextPage();
//        int pageNum = punsPage.getPages();

        Map<Object, Object> punMap = new HashMap();
        punMap.put("puns", puns);


//        punMap.put("nextPage", nextPage);
//        punMap.put("prePage", prePage);
//        punMap.put("pageNum", pageNum);

        return punMap;
    }

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

    @RequestMapping("/pun/addPunchCard")
    @ResponseBody
    public void addPunchCard(Card card){

        punchCardService.insertCard(card);
    }

}
