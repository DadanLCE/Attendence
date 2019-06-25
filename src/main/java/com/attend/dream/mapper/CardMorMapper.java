package com.attend.dream.mapper;

import com.attend.dream.domain.CardEve;
import com.attend.dream.domain.CardMor;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface CardMorMapper {

    //通过id来查询 打卡单
    @Select("select id, cardCode, name, date, note " +
            "from cardMor where id = #{id}")
    CardMor getCardMorById(int id);

    //在打卡单  通过cardCode的 模糊查询
    @Select("select id, cardCode, name, date , note  " +
            "from cardMor where cardCode like concat('%',#{cardCode},'%') ")
    List<CardMor> getCardMorByCode(String cardCode);

    //插入数据打卡单
    @Insert("insert into cardMor (cardCode, name, date ,note ) " +
            "values(#{cardCode}, #{name}, #{date}, #{note}) ")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    Boolean insertCardMor(CardMor card);

    //更新数据 补卡单
    @Update("update cardMor set cardCode=#{cardCode}, name=#{name}, date=#{date}, note=#{note} " +
            "where id = #{id}")
    Boolean updateCard(CardMor card);

    //删除数据 打卡单
    @Delete("delete from cardMor where id=#{id}")
    Boolean deleteCard(int id);
}
