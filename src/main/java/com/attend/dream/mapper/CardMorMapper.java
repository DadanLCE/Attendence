package com.attend.dream.mapper;

import com.attend.dream.domain.CardEve;
import com.attend.dream.domain.CardMor;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CardMorMapper {



    //通过id来查询 打卡单
    @Select("select id, cardCode, name, date, note " +
            "from cardMor where id = #{id}")
    CardMor getCardMorById(int id);

//    //用于结算当天的出勤情况
//    @Select("select id,cardCode,name,date,note" +
//            "from card where cardCode = #{cardCode}")
//    List<Card> getCardsToday(String cardCode);


    //在打卡单  通过cardCode的 模糊查询
    @Select("select id, cardCode, name, date , note  " +
            "from cardMor where cardCode like concat('%',#{cardCode},'%')" +
            " and flag = 'true' ")
    List<CardMor> getCardsMorByCode(String cardCode);

    //在打卡单  通过cardCode的 单个查询
    @Select("select id, cardCode, name, date , note  " +
            "from cardMor where cardCode =#{cardCode} " +
            " and flag = 'true' ")
    CardMor getCardMorByCode(String cardCode);

    //在补卡单  通过cardCode的 模糊查询
    @Select("select id, cardCode, name, date , note  " +
            "from cardMor where cardCode like concat('%',#{cardCode},'%') " +
            " and flag = 'false' ")
    List<CardMor> getCardsMorByCodeFlag(String cardCode);

    //在打卡单  通过cardCode的 单个查询
    @Select("select id, cardCode, name, date , note  " +
            "from cardMor where cardCode =#{cardCode} " +
            " and flag = 'false' ")
    CardMor getCardMorByCodeFlag(String cardCode);

//    //在考勤表 获得两个日期之间的查询内容 通过cardCode的 模糊查询
//    @Select("select id, cardCode, name, date, isRepair " +
//            "from card where cardCode like concat('%',#{cardCode},'%') " +
//            "and date between #{preDate} and #{nextDate}")
//    List<Card> getCardsByCodeByDate(@Param("cardCode") String cardCode,@Param("preDate") Date preDate,@Param("nextDate") Date nextDate);


    //插入数据
    @Insert("insert into cardMor (cardCode, name, date ,note, flag ) " +
            "values(#{cardCode}, #{name}, #{date}, #{note} , #{flag} ) ")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    Boolean insertCardMor(CardMor card);


    //更新数据
    @Update("update cardMor set cardCode=#{cardCode}, name=#{name}, date=#{date}, note=#{note} " +
            "where id = #{id}")
    Boolean updateCard(CardMor card);

    //删除数据
    @Delete("delete from cardMor where id=#{id}")
    Boolean deleteCard(int id);
}
