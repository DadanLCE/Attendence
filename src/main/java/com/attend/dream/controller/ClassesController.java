package com.attend.dream.controller;

import com.attend.dream.domain.Classes;
import com.attend.dream.service.ClassesService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ClassesController {

    @Autowired
    ClassesService classesService;

    //查询
    @RequestMapping(value = "/cla", method = RequestMethod.POST)
    @ResponseBody
    public Map<Object, Object> getClassesByCode(@RequestParam(value = "currentPage") int currentPage,
                                                  @RequestParam(value = "pageSize", defaultValue = "5") int pageSize, @RequestParam(value = "claCode") String claCode){
        PageInfo<Classes> clasPage = classesService.getClassesByCodePage(currentPage,pageSize,claCode);
        List<Classes> clas = classesService.getClassesByCode(currentPage,pageSize,claCode);
        System.out.println(clas);
        System.out.println(clasPage);
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        int prePage = clasPage.getPrePage();
        int nextPage = clasPage.getNextPage();
        int pageNum = clasPage.getPages();

        Map<Object, Object> claMap = new HashMap();
        claMap.put("clas", clas);
        claMap.put("nextPage", nextPage);
        claMap.put("prePage", prePage);
        claMap.put("pageNum", pageNum);

        return claMap;
    }

    //批量删除
    @PostMapping("/cla/delClas")
    public String empsDelete(String claList){
        System.out.println(claList);

        String[] strs = claList.split(",");
        for (int i = 0; i < strs.length; i++) {
            classesService.deleteCla(Integer.parseInt(strs[i]));
        }
        return "redirect:/employee?currentPage=1";

    }

    //删除单行
    @PostMapping("/cla/delClaById/{id}")
    public String deleteClass(@PathVariable(value = "id") int claId){
        classesService.deleteCla(claId);
        return "redirect:/employee?currentPage=1";
    }

    //添加
    @PostMapping("/cla/addClass")
    @ResponseBody
    public String addClass(Classes cla, Map<String,Object> map) {

        String fallBack = classesService.insertCla(cla);
        //也可以直接return fallBack;
       return fallBack; //1是成功，2是班次已存在

    }

}
