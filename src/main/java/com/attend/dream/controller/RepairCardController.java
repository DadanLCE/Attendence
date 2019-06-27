package com.attend.dream.controller;

import com.attend.dream.domain.RepairCard;
import com.attend.dream.service.RepairCardService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    //添加补卡记录
    @PostMapping("/rep/addRepairCard")
    @ResponseBody
    public String addRepairCard(RepairCard rep, Map<String,Object> map) {

        String fallBack = repairCardService.inserRepairCard(rep);
        return fallBack;
    }

    //删除
    @PostMapping("/rep/delete")
    @ResponseBody
    public void deleteEmployee(int id){
        repairCardService.deleteRepairCard(id);
    }

    //批量删除
    @PostMapping("/rep/delReps")
    @ResponseBody
    public void repsDelete(String repList){
        String[] strs = repList.split(",");
        for (int i = 0; i < strs.length; i++) {
            repairCardService.deleteRepairCard(Integer.parseInt(strs[i]));
        }
    }
}
