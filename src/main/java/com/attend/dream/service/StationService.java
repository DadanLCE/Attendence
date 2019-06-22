package com.attend.dream.service;

import com.attend.dream.domain.Department;
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


    //添加岗位
    public String insertStation(Station s) {

        String staCode = s.getStaCode();

        /**
         * 判断是否已经存在岗位编码
         */

        if (stationMapper.getStationByStaCode(staCode) != null ) {
            return "2";
            /**
             * 判断是否有上级部门
             */
        } else if ( stationMapper.getStationByStaCode(s.getStaBoss()) == null ){
            return "3";
        } else {
           stationMapper.insertStation(s);
            return "1";
        }

    }

}
