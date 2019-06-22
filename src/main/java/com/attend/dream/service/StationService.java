package com.attend.dream.service;

import com.attend.dream.domain.Employee;
import com.attend.dream.domain.Station;
import com.attend.dream.mapper.StationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StationService {

    @Autowired(required = false)
    StationMapper stationMapper;

    //显示岗位列表
    public List<Station> getStations(){
        List<Station> list = stationMapper.getStations();
        return list;
    }

    //通过编码查询岗位
    public List<Station> getStationByName(String staCode){
        List<Station> station = stationMapper.getStationsByCode(staCode);
        return station;
    }

    //删除单个岗位
    public boolean deleteStation(int staId){
        stationMapper.delete(staId);
        return true;
    }

    //添加员工
    public boolean insertStation(Station e) {

        String uniqueCode = e.getStaCode();
        Station station = stationMapper.getStationByStaCode(uniqueCode);
        if (station == null) {
            stationMapper.insertStation(e);
            return true;
        }else {
            return true;
        }

    }

}
