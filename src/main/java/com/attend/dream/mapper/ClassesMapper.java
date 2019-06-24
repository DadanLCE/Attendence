package com.attend.dream.mapper;

import com.attend.dream.domain.Classes;
import com.attend.dream.domain.Station;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ClassesMapper {

    //模糊查询
    @Select("select id,claCode,claName,claMorTime,claEveTime,claNote \n" +
            "from classes \n" +
            "where claCode LIKE CONCAT('%',#{claCode},'%') ")
    List<Classes> getClassesByCalCode(String claCode);

    //根据ID查询
    @Select("select id,claCode,claName,claMorTime,claEveTime,claNote \n" +
            "from classes " +
            "where id = #{id}")
    Classes getClassesById(int id);

    //插入
    @Insert("insert into classes(claCode, claName,claMorTime, claEveTime, claNote) " +
            "values(#{claCode}, #{claName}, #{claMorTime}, #{claEveTime}, #{claNote})")
    @Options(useGeneratedKeys = true, keyProperty = "empId")
    Boolean insertClasses(Classes classes);

    //更新
    @Update("update classes set claCode=#{claCode}, claName=#{claName}, claMorTime=#{claMorTime},claEveTime=#{claEveTime}," +
            " claNote=#{claNote} where id=#{id}" )
    Boolean updateClasses(Classes classes);

    //删除
    @Delete("delete from classes where id=#{id}")
    Boolean deleteClasses(int id);

}
