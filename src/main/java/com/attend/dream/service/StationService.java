package com.attend.dream.service;

import com.attend.dream.domain.Department;
import com.attend.dream.domain.Employee;
import com.attend.dream.domain.Station;
import com.attend.dream.mapper.StationMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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

    //模糊查询
    //通过编码查询页面信息
    public PageInfo<Station> getStationBystaCodePage(int currentPage, int pageSize, String staCode){
        PageHelper.startPage(currentPage, pageSize);
        List<Station> stas = stationMapper.getStationsByStaCode(staCode);
        PageInfo<Station> pageInfo = new PageInfo<>(stas);
        return pageInfo;
    }
    //查询到岗位的信息
    public List<Station> getStationBystaCode(int currentPage, int pageSize, String staCode){
        PageHelper.startPage(currentPage, pageSize);
        List<Station> stas = stationMapper.getStationsByStaCode(staCode);
        return stas;
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

    public Station getStationById(int id){
        Station sta = stationMapper.getStationById(id);
        return  sta;
    }

}
