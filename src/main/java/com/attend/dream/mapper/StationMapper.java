package com.attend.dream.mapper;

import com.attend.dream.domain.Employee;
import com.attend.dream.domain.Station;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface StationMapper {
    //查询所有
    @Select("select id,staCode,staName,staDep,staBoss,staCate,staDes\n" +
            "        from station")
    List<Station> getStations();

    //查询单个
    @Select("select id,staCode,staName,staDep,staBoss,staCate,staDes\n" +
            "        from station " +
            "where staCode=#{staCode}")
    Station getStationByStaCode(String staCode);

    //查询单个模糊查询
    @Select("select id,staCode,staName,staDep,staBoss,staCate,staDes\n" +
            "        from station " +
            "where staCode LIKE CONCAT('%',#{depCode},'%') ")
    List<Station> getStationsByCode(String staCode);

    //添加
    @Insert("insert into station(staCode,staName,staDep,staBoss,staCate,staDes)" +
            "values(#{staCode},#{staName},#{staDep},#{staBoss},#{staCate},#{staDes})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    Boolean insertStation(Station station);

    //更新
    @Update("update station set staCode=#{staCode},staName=#{staName},staDep=#{staDep},staBoss=#{staBoss}," +
            "staCate=#{staCate},staDes=#{staDes} \n " +
            "where id =#{id}")
    Boolean updateStation(Station station);

    //删除
    @Delete("delete from station where id = #{id}")
    Boolean delete(int id);
}
