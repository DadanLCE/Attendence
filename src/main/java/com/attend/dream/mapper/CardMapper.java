package com.attend.dream.mapper;

import com.attend.dream.domain.Card;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

public interface CardMapper {

    //通过id来查询
    @Select("select id, cardCode, name, date, isRepair " +
            "from card where id = #{id}")
    Card getCardById(int id);

    //在考勤表 通过cardCode的 模糊查询
    @Select("select id, cardCode, name, date, isRepair " +
            "from card where cardCode like concat('%',#{cardCode},'%')")
    List<Card> getCardsByCode(String cardCode);

    //在打卡单 isRepair = false 通过cardCode的 模糊查询
    @Select("select id, cardCode, name, date, isRepair " +
            "from card where cardCode like concat('%',#{cardCode},'%') and isRepair = 'false' ")
    List<Card> getCardsByCodeFalse(String cardCode);

    //在补卡单 isRepair = true 通过cardCode的 模糊查询
    @Select("select id, cardCode, name, date, isRepair " +
            "from card where cardCode like concat('%',#{cardCode},'%') and isRepair = 'true' ")
    List<Card> getCardsByCodeTrue(String cardCode);

    //在考勤表 获得两个日期之间的查询内容 通过cardCode的 模糊查询
    @Select("select id, cardCode, name, date, isRepair " +
            "from card where cardCode like concat('%',#{cardCode},'%') " +
            "and date between #{preDate} and #{nextDate}")
    List<Card> getCardsByCodeByDate(@Param("cardCode") String cardCode,@Param("preDate") Date preDate,@Param("nextDate") Date nextDate);


    //插入数据
    @Insert("insert into card (cardCode, name, date , isRepair) " +
            "values(#{cardCode}, #{name}, #{date}, #{isRepair})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    Boolean insertCard(Card card);

    //更新数据
    @Update("update card set cardCode=#{cardCode}, name=#{name}, date=#{date}, isRepair=#{isRepair} " +
            "where id = #{id}")
    Boolean updateCard(Card card);

    @Delete("delete from card where id=#{id}")
    Boolean deleteCard(int id);

}
