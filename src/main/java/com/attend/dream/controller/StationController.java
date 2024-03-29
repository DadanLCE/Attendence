package com.attend.dream.controller;

import com.attend.dream.domain.Employee;
import com.attend.dream.domain.Station;
import com.attend.dream.service.EmployeeService;
import com.attend.dream.service.StationService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class StationController {

    @Autowired
    StationService stationService;


    //查询功能
    @RequestMapping(value = "/sta", method = RequestMethod.POST)
    @ResponseBody
    public Map<Object, Object> getEmployeesByName(@RequestParam(value = "currentPage") int currentPage,
                                                  @RequestParam(value = "pageSize", defaultValue = "5") int pageSize,@RequestParam(value = "staCode") String staCode){
        PageInfo<Station> stasPage = stationService.getStationBystaCodePage(currentPage, pageSize, staCode);
        List<Station> stas = stationService.getStationBystaCode(currentPage, pageSize, staCode);
        int prePage = stasPage.getPrePage();
        int nextPage = stasPage.getNextPage();
        int pageNum = stasPage.getPages();
        Map<Object, Object> empMap = new HashMap();
        empMap.put("stas", stas);
        empMap.put("nextPage", nextPage);
        empMap.put("prePage", prePage);
        empMap.put("pageNum", pageNum);
        return empMap;
    }

    //显示全部岗位列表
    @RequestMapping("/station")
    public String list(Model model, @RequestParam(value = "currentPage") int currentPage,
                       @RequestParam(value = "pageSize", defaultValue = "5") int pageSize, @RequestParam(value = "staCode") String staCode){
        PageInfo<Station> stasPage = stationService.getStationBystaCodePage(currentPage, pageSize, staCode);
        List<Station> stas = stationService.getStationBystaCode(currentPage, pageSize, staCode);
        model.addAttribute("stas",stas);
        model.addAttribute("stasPage",stasPage);
        //传递staCode到station_list 的下一页和上一页
        model.addAttribute("staCode",staCode);
        return "station_list";
    }

    //跳转添加页面，后来采用模态框
//    @GetMapping("/sta/goToAddHtml")
//    public String gotoAddEmployee() {
//        return "add_station";
//    }

    //删除单行员工信息
    @PostMapping("/sta/staDel")
    @ResponseBody
    public String deleteEmployee(int clickId){
        System.out.println(clickId);
        stationService.deleteStation(clickId);
        return "null";
    }

    //批量删除员工
    @PostMapping("/sta/stasDel")
    @ResponseBody
    public String stasDelete(String staList){
        String[] strs = staList.split(",");
        System.out.println(strs[0]);
        for (int i = 0; i < strs.length; i++) {
            stationService.deleteStation(Integer.parseInt(strs[i]));
        }
        return "null";

    }

    //添加员工
    @PostMapping("/sta/addStation")
    @ResponseBody
    public String addStation(Station sta, Map<String,Object> map) {

        String fallBack = stationService.insertStation(sta);
        System.out.println(sta);
        if ( fallBack.equals("1")) {
            return fallBack;
        } else if ( fallBack.equals("2")){
            map.put("msg","岗位已存在");
            return fallBack;
        } else if (fallBack.equals("3")) {
            map.put("msg","上级部门不存在");
            return fallBack;
        } else{
            return "error";
        }


    }

    //获取前端ID值，查询信息并返回
    @GetMapping("/sta/getStaById/{id}")
    @ResponseBody
    public Station getStaById(@PathVariable(value = "id") int staId) {
        Station sta = stationService.getStaById(staId);
        return sta;

    }

    //更新信息
    @PostMapping("/sta/updateSta")
    @ResponseBody
    public String updateStation(Station s) {
        String fallBack = stationService.updateStation(s);
        return fallBack;
    }

    //在添加员工中，获取显示已有的岗位进行下拉框选择,避免错误.
    @GetMapping("/sta/getStations")
    @ResponseBody
    public List<Station> getStations() {
        List<Station> stas = stationService.getStations();
        return stas;
    }

}
