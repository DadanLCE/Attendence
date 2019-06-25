package com.attend.dream.mapper;

import com.attend.dream.domain.Card;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

public interface CardMapper {



    //通过id来查询 补卡单
    @Select("select id, cardCode, name, date, note " +
            "from card where id = #{id}")
    Card getCardRepairById(int id);

//    //用于结算当天的出勤情况
//    @Select("select id,cardCode,name,date,note" +
//            "from card where cardCode = #{cardCode}")
//    List<Card> getCardsToday(String cardCode);



    //在补卡单  通过cardCode的 模糊查询
    @Select("select id, cardCode, name, date, note " +
            "from cardRepair where cardCode like concat('%',#{cardCode},'%') ")
    List<Card> getCardRepairsByCode(String cardCode);

//    //在考勤表 获得两个日期之间的查询内容 通过cardCode的 模糊查询
//    @Select("select id, cardCode, name, date, isRepair " +
//            "from card where cardCode like concat('%',#{cardCode},'%') " +
//            "and date between #{preDate} and #{nextDate}")
//    List<Card> getCardsByCodeByDate(@Param("cardCode") String cardCode,@Param("preDate") Date preDate,@Param("nextDate") Date nextDate);




    //插入数据补卡卡单
    @Insert("insert into cardRepair (cardCode, name, date ,note ) " +
            "values(#{cardCode}, #{name}, #{date} , #{note})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    Boolean insertCardRepair(Card card);

    //插入数据打卡单
    @Insert("insert into card (cardCode, name, date ,note ) " +
            "values(#{cardCode}, #{name}, #{date}, #{note}) ")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    Boolean insertCard(Card card);

    //更新数据 打卡单
    @Update("update card set cardCode=#{cardCode}, name=#{name}, date=#{date}, note=#{note} " +
            "where id = #{id}")
    Boolean updateCard(Card card);

    //删除数据 打卡单
    @Delete("delete from card where id=#{id}")
    Boolean deleteCard(int id);


    //在打卡单  通过cardCode的 模糊查询
    @Select("select id, cardCode, name, date , note  " +
            "from card where cardCode like concat('%',#{cardCode},'%') ")
    List<Card> getCardsByCode(String cardCode);

    //通过id来查询 打卡单
    @Select("select id, cardCode, name, date, note " +
            "from card where id = #{id}")
    Card getCardById(int id);



}
