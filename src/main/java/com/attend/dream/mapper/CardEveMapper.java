package com.attend.dream.mapper;

import com.attend.dream.domain.CardEve;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface CardEveMapper {



    //通过id来查询 打卡单
    @Select("select id, cardCode, name, date, note " +
            "from cardEve where id = #{id}")
    CardEve getCardEveById(int id);

//    //用于结算当天的出勤情况
//    @Select("select id,cardCode,name,date,note" +
//            "from card where cardCode = #{cardCode}")
//    List<Card> getCardsToday(String cardCode);


//    在打卡单  通过cardCode的 模糊查询
    @Select("select id, cardCode, name, date , note  " +
            "from cardEve where cardCode like concat('%',#{cardCode},'%') ")
    List<CardEve> getCardEvesByCode(String cardCode);


    @Select("SELECT id,cardCode,date FROM cardEve where cardCode like concat('%',#{cardCode},'%')\n" +
            "UNION\n" +
            "SELECT id,cardCode,date FROM cardMor where cardCode like concat('%',#{cardCode},'%')")
    List<CardEve> getCardAllByCode(String cardCode);

//    //在考勤表 获得两个日期之间的查询内容 通过cardCode的 模糊查询
//    @Select("select id, cardCode, name, date, isRepair " +
//            "from card where cardCode like concat('%',#{cardCode},'%') " +
//            "and date between #{preDate} and #{nextDate}")
//    List<Card> getCardsByCodeByDate(@Param("cardCode") String cardCode,@Param("preDate") Date preDate,@Param("nextDate") Date nextDate);


    //插入数据打卡单
    @Insert("insert into cardEve (cardCode, name, date ,note ) " +
            "values(#{cardCode}, #{name}, #{date}, #{note}) ")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    Boolean insertCardEve(CardEve card);


    //更新数据 补卡单
    @Update("update cardEve set cardCode=#{cardCode}, name=#{name}, date=#{date}, note=#{note} " +
            "where id = #{id}")
    Boolean updateCard(CardEve card);

    //删除数据 打卡单
    @Delete("delete from cardEve where id=#{id}")
    Boolean deleteCard(int id);
}
