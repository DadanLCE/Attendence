package com.attend.dream.mapper;


import com.attend.dream.domain.Card;
import com.attend.dream.domain.CardMor;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface PunchCardMapper {

    //通过id来查询 打卡单
    @Select("select id, cardCode, name, eveTime,morTime, note " +
            "from punchCard where id = #{id}  ")
    CardMor getCardById(int id);

//    @Select("select * from punchCard")
    //插入早上打卡数据
    @Insert("insert into punchCard (cardCode, name, morTime,note) " +
            "values(#{cardCode},#{name},#{morTime}, #{note} ) ")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertMorCard(CardMor card);

    //插入晚上打卡数据
    @Insert("insert into punchCard (cardCode, name, eveTime,note) " +
            "values(#{cardCode},#{name},#{eveTime} #{note} ) ")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertEveCard(CardMor card);

    //在打卡单  通过cardCode的 模糊查询
    @Select("select *" +
            "from punchCard where cardCode like concat('%',#{cardCode},'%')")
    List<Card> getCardsByCode(String cardCode);

//
//    //更新数据
//    @Update("update cardMor set cardCode=#{cardCode}, name=#{name}, date=#{date}, note=#{note} " +
//            "where id = #{id}")
//    Boolean updateCard(CardMor card);
//
//    //删除数据
//    @Delete("delete from cardMor where id=#{id}")
//    Boolean deleteCard(int id);





}
