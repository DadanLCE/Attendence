package com.attend.dream.mapper;


import com.attend.dream.domain.Card;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

public interface PunchCardMapper {

    //通过id来查询 打卡单
    @Select("select id, cardCode, name, eveTime,morTime, note " +
            "from punchCard where id = #{id}  ")
    Card getCardById(int id);

    //插入早上打卡数据
    @Insert("insert into punchCard (cardCode, name, morTime,note) " +
            "values(#{cardCode},#{name},#{morTime}, #{note} ) ")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertMorCard(Card card);

    //插入晚上打卡数据
    @Insert("insert into punchCard (cardCode, name, eveTime,note) " +
            "values(#{cardCode},#{name},#{eveTime} ,#{note} ) ")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertEveCard(Card card);

    //在打卡单  通过cardCode的 模糊查询
    @Select("select *" +
            "from punchCard where cardCode like concat('%',#{cardCode},'%')")
    List<Card> getCardsByCode(String cardCode);

    //开始时间和结束时间的模糊查询
    @Select("select *" +
            "from punchCard where cardCode like concat('%',#{cardCode},'%')" +
            " and morTime between #{preDate} and #{nextDate}")
    List<Card> getCardsByCodeTime(@Param("cardCode")String cardCode, @Param("preDate") Date preDate, @Param("nextDate") Date nextDate);


    @Update("update punchCard set eveTime=#{eveTime} " +
            "where id = #{id} ")
    Boolean updateCard(Card card);

    //更新数据
    @Update("update punchCard set  eveTime=#{eveTime}, morTime=#{morTime}, note=#{note} " +
            "where cardCode = #{cardCode}")
    Boolean updateCard1(Card card);

    //  根据用户的姓名查询打卡的次数
    @Select("SELECT COUNT(*) FROM punchCard where name = #{name}")
    int cardsNumEchart(String uname);

    //查询打卡单有多少员工
    @Select("select distinct name from punchCard")
    List<Card> cardsEchart();



}
