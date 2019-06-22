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
    @DeleteMapping("/sta/stasDel")
    public String deleteEmployees(@RequestParam(value = "staIds") int[] staIds){
        for(int i : staIds)
            stationService.deleteStation(i);
        return "redirect:/station";
    }


//    //添加员工
    @PostMapping("/sta/addStation")
    public String addStation(Station sta, Map<String,Object> map) {
//        String staCode = sta.getStaCode();
        stationService.insertStation(sta);
        return "index";

    }
}
