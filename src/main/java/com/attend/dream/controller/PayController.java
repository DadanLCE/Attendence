package com.attend.dream.controller;

import com.attend.dream.domain.Department;
import com.attend.dream.domain.Pay;
import com.attend.dream.service.PayService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class PayController {
    @Autowired
    private PayService payService;
//
//
//    //查询，对应前端tbody
//    @RequestMapping(value = "/pay", method = RequestMethod.POST)
//    @ResponseBody
//    public Map<Object, Object> getPaysByempCode(@RequestParam(value = "currentPage") int currentPage,
//                                                    @RequestParam(value = "pageSize", defaultValue = "5") int pageSize, @RequestParam(value = "empCode") String empCode){
//        PageInfo<Pay> paysPage = payService.getPaysByEmpCodePage(currentPage,pageSize,empCode);
//        List<Pay> pays = payService.getPaysByEmpCode(currentPage,pageSize,empCode);
//        int prePage = paysPage.getPrePage();
//        int nextPage = paysPage.getNextPage();
//        int pageNum = paysPage.getPages();
//
//        Map<Object, Object> payMap = new HashMap();
//        payMap.put("pays", pays);
//        payMap.put("nextPage", nextPage);
//        payMap.put("prePage", prePage);
//        payMap.put("pageNum", pageNum);
//        return payMap;
//    }

    //查询员工通过名字 模糊查询 分页
    @RequestMapping(value = "/pay", method = RequestMethod.POST)
    @ResponseBody
    public Map<Object, Object> getPaysByCode(Model model, @RequestParam(value = "currentPage") int currentPage,
                                             @RequestParam(value = "pageSize", defaultValue = "5") int pageSize, @RequestParam(value = "empCode") String empCode) {
        PageInfo<Pay> paysPage = payService.getPaysByEmpCodePage(currentPage, pageSize, empCode);
        List<Pay> pays = payService.getPaysByEmpCode(currentPage, pageSize, empCode);
        int prePage = paysPage.getPrePage();
        int nextPage = paysPage.getNextPage();
        int pageNum = paysPage.getPages();
        Map<Object, Object> payMap = new HashMap();
        payMap.put("pays", pays);
        payMap.put("nextPage", nextPage);
        payMap.put("prePage", prePage);
        payMap.put("pageNum", pageNum);
        System.out.println(pays);
        return payMap;
    }


}
