package com.attend.dream.mapper;

import com.attend.dream.domain.RepairCard;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

public interface RepairCardMapper {
    //通过id来查询 打卡单
    @Select("select id, cardCode, name, time, note  " +
            "from repairCard where id = #{id}  ")
    RepairCard getCardById(int id);

    @Select("select id, cardCode, name, time , note  " +
            "from repairCard where cardCode like concat('%',#{cardCode},'%')")
    List<RepairCard> getCardsByCode(String cardCode);




    @Insert("insert into repairCard ( cardCode, name,time, note) " +
            "values(#{cardCode}, #{name}, #{time} , #{note} ) ")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    Boolean insertAll(RepairCard card);



    //删除数据
    @Delete("delete from repairCard where id=#{id}")
    Boolean deleteCard(int id);

    @Select("select cardCode, COUNT(*) sum from punchCard\n" +
            "group by cardCode;")
    List<Map> checkCardSum();
//    //插入数据mor
//    @Insert("insert into repairCard ( cardCode, name, morTime, note) " +
//            "values(#{cardCode}, #{name}, #{morTime}, #{note} ) ")
//    @Options(useGeneratedKeys = true, keyProperty = "id")
//    Boolean insertMor(RepairCard card);
//
//    //插入数据eve
//    @Insert("insert into repairCard ( cardCode, name, eveTime, note) " +
//            "values(#{cardCode}, #{name}, #{eveTime}, #{note} ) ")
//    @Options(useGeneratedKeys = true, keyProperty = "id")
//    Boolean insertEve(RepairCard card);

//    //更新数据
//    @Update("update repairCard set cardCode=#{cardCode}, name=#{name}, date=#{date}, note=#{note} " +
//            "where id = #{id}")
//    Boolean updateCard(CardMor card);

}
