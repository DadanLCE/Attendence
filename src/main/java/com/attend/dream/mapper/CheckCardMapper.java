package com.attend.dream.mapper;

import com.attend.dream.domain.Card;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

public interface CheckCardMapper {

    //通过id来查询 打卡单
    @Select("select id, cardCode, name, eveTime,morTime, note " +
            "from checkCard where id = #{id}  ")
    Card getCardById(int id);

    //通过cardCode来查询 打卡单 单条数据
    @Select("select id, cardCode, name, eveTime,morTime, note " +
            "from checkCard where cardCode = #{cardCode}  ")
    List<Card> getCardByCode(String cardCode);

    //插入早上打卡数据
    @Insert("insert into checkCard (cardCode, name, morTime,note) " +
            "values(#{cardCode},#{name},#{morTime}, #{note} ) ")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertMorCard(Card card);

    //插入晚上打卡数据
    @Insert("insert into checkCard (cardCode, name, eveTime,note) " +
            "values(#{cardCode},#{name},#{eveTime} #{note} ) ")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertEveCard(Card card);

    //插入数据
    @Insert("insert into checkCard (cardCode, name, morTime, eveTime,note) " +
            "values(#{cardCode},#{name},#{morTime},#{eveTime} ,#{note} ) ")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertAllDayCard(Card card);

    //在打卡单  通过cardCode的 模糊查询
    @Select("select *" +
            "from checkCard where cardCode like concat('%',#{cardCode},'%')")
    List<Card> getCardsByCode(String cardCode);

    //开始时间和结束时间的模糊查询
    @Select("select *" +
            "from checkCard where cardCode like concat('%',#{cardCode},'%')" +
            " and morTime between #{preDate} and #{nextDate}")
    List<Card> getCardsByCodeTime(@Param("cardCode")String cardCode, @Param("preDate") Date preDate, @Param("nextDate") Date nextDate);

    //开始时间和结束时间的模糊查询
    @Select("select *" +
            "from checkCard where cardCode like concat('%',#{cardCode},'%')" +
            " and morTime between #{preDate} and #{nextDate}")
    List<Card> TestTest(@Param("cardCode")String cardCode, @Param("preDate") Date preDate, @Param("nextDate") Date nextDate);

    //更新数据
    @Update("update checkCard set  eveTime=#{eveTime}, morTime=#{morTime}, note=#{note} " +
            "where cardCode = #{cardCode}")
    Boolean updateCard(Card card);

    //更新早上数据
    @Update("update checkCard set morTime=#{morTime}, note=#{note} " +
            "where cardCode = #{cardCode} and eveTime =#{eveTime}")
    Boolean updateMorCard(Card card);

    //更新数据
    @Update("update checkCard set eveTime=#{eveTime}, note=#{note} " +
            "where cardCode = #{cardCode} and morTime=#{morTime} ")
    Boolean updateEveCard(Card card);

    //清空表
    @Update("truncate table checkCard")
    Boolean delete();
}
