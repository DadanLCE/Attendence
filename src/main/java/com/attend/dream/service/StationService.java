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

    public List<Station> getStations(){
        List<Station> list = stationMapper.getStations();
        return list;
    }

    public List<Station> getStationByName(String staCode){
        List<Station> station = stationMapper.getStationsByCode(staCode);
        return station;
    }

    //添加员工
//    public boolean insertStation(Station e) {
//
//        String uniqueCode = e.getStaCode();
//        Station station = stationMapper.isEmpCodeExist(uniqueCode);
//        if (employee == null) {
//            employeesMapper.insertEmployee(e);
//            return true;
//        }else {
//            return true;
//        }
//
//    }
//
//    public boolean deleteEmployee(int empId){
//        employeesMapper.deleteEmployee(empId);
//        return true;
//    }
//
//    public boolean deleteEmployees(List<Employee> employees){
//        for(Employee e : employees){
//            employeesMapper.deleteEmployee(e.getEmpId());
//        }
//        return true;
//    }
}
