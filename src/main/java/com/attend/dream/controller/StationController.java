package com.attend.dream.controller;

import com.attend.dream.domain.Employee;
import com.attend.dream.domain.Station;
import com.attend.dream.service.EmployeeService;
import com.attend.dream.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Controller
public class StationController {

    @Autowired
    StationService stationService;

    //显示全部岗位列表
    @GetMapping("/station")
    public String list(Model model){
        Collection<Station> stations=stationService.getStations();
        model.addAttribute("stas",stations);
        return "station_list";
    }

    //查询岗位
    @PostMapping("/sta/get")
    public String getStationsByName(@RequestParam(value = "staCode") String staCode, Model model){
        List<Station> station = stationService.getStationByName(staCode);
        model.addAttribute("stas",station);
        return "station_list";
    }

    @GetMapping("/sta/goToAddHtml")
    public String gotoAddEmployee() {
        return "add_station";
    }

    //删除单行员工信息
    @DeleteMapping("/sta/{staId}")
    public String deleteEmployee(@PathVariable(value = "staId") int staId){
        stationService.deleteStation(staId);
        return "redirect:/station";
    }

    //批量删除员工
    @PostMapping("/sta/stasDel")
    public String stasDelete(String staList){
        String[] strs = staList.split(",");
        for (int i = 0; i < strs.length; i++) {
            stationService.deleteStation(Integer.parseInt(strs[i]));
        }
        return "station_list";

    }

    //添加员工
    @PostMapping("/sta/addStation")
    public String addStation(Station sta, Map<String,Object> map) {

        String fallBack = stationService.insertStation(sta);

        if ( fallBack.equals("1")) {
            return "redirect:/station";
        } else if ( fallBack.equals("2")){
            map.put("msg","岗位已存在");
            return "add_station";
        } else if (fallBack.equals("3")) {
            map.put("msg","上级部门不存在");
            return "add_station";
        } else{
            return "index";
        }

    }

}
