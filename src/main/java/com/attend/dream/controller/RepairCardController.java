package com.attend.dream.controller;

import com.attend.dream.domain.RepairCard;
import com.attend.dream.service.RepairCardService;
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
public class RepairCardController {

    @Autowired
    RepairCardService repairCardService;

    //查询，对应前端tbody
    @RequestMapping(value = "/rep", method = RequestMethod.POST)
    @ResponseBody
    public Map<Object, Object> getRepairCardsByCode(@RequestParam(value = "currentPage") int currentPage,
                                                  @RequestParam(value = "pageSize", defaultValue = "5") int pageSize, @RequestParam(value = "cardCode") String cardCode){
//        PageInfo<RepairCard> empsPage = repairCardService.getCardsByCode(cardCode);
        List<RepairCard> reps = repairCardService.getCardsByCode(cardCode);
//        int prePage = empsPage.getPrePage();
//        int nextPage = empsPage.getNextPage();
//        int pageNum = empsPage.getPages();

        Map<Object, Object> repMap = new HashMap();
        repMap.put("reps", reps);
//        empMap.put("nextPage", nextPage);
//        empMap.put("prePage", prePage);
//        empMap.put("pageNum", pageNum);

        return repMap;
    }

}
