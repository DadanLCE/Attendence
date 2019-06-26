package com.attend.dream.mapper;

import com.attend.dream.domain.Card;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

public interface CheckCardMapper {

    //通过id来查询 打卡单
    @Select("select id, cardCode, name, eveTime,morTime, note " +
            "from checkCard where id = #{id}  ")
    Card getCardById(int id);



    //插入晚上打卡数据
    @Insert("insert into checkCard (cardCode, name, morTime, eveTime,note) " +
            "values(#{cardCode},#{name},#{morTime},#{eveTime} #{note} ) ")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertEveCard(Card card);

    //在打卡单  通过cardCode的 模糊查询
    @Select("select *" +
            "from checkCard where cardCode like concat('%',#{cardCode},'%')")
    List<Card> getCardsByCode(String cardCode);

    //开始时间和结束时间的模糊查询
    @Select("select *" +
            "from checkCard where cardCode like concat('%',#{cardCode},'%')" +
            " and morTime between #{preDate} and #{nextDate}")
    List<Card> getCardsByCodeTime(@Param("cardCode")String cardCode, @Param("preDate") Date preDate, @Param("nextDate") Date nextDate);
}
