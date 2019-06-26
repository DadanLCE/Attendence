package com.attend.dream.mapper;

import com.attend.dream.domain.CardMor;
import com.attend.dream.domain.RepairCard;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface RepairCardMapper {
    //通过id来查询 打卡单
    @Select("select id, cardCode, name, morTime, eveTime, note  " +
            "from repairCard where id = #{id}  ")
    RepairCard getCardById(int id);

    @Select("select id, cardCode, name, morTime, eveTime, note  " +
            "from repairCard where cardCode like concat('%',#{cardCode},'%')")
    List<RepairCard> getCardsByCode(String cardCode);


    @Insert("insert into repairCard ( cardCode, name, morTime, eveTime, note) " +
            "values(#{cardCode}, #{name}, #{morTime},#{eveTime}, #{note} ) ")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    Boolean insertAll(RepairCard card);


    //删除数据
    @Delete("delete from repairCard where id=#{id}")
    Boolean deleteCard(int id);


}
