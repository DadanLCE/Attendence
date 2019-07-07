package com.attend.dream.mapper;


import com.attend.dream.domain.RepairCard;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

public interface RepairCardMapper {
    //通过id来查询 打卡单
    @Select("select id, cardCode, name, time, note  " +
            "from repairCard where id = #{id}  ")
    RepairCard getCardById(int id);

    //模糊查询获取补卡单
    @Select("select id, cardCode, name, time , note  " +
            "from repairCard where cardCode like concat('%',#{cardCode},'%')")
    List<RepairCard> getCardsByCode(String cardCode);

    //添加补卡单
    @Insert("insert into repairCard ( cardCode, name,time, note) " +
            "values(#{cardCode}, #{name}, #{time} , #{note} ) ")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    Boolean insertAll(RepairCard card);

    //删除数据
    @Delete("delete from repairCard where id=#{id}")
    Boolean deleteCard(int id);

    @Select("select cardCode, COUNT(*) sum from punchCard\n" +
            "group by cardCode;")
    List<Map> checkCardSum();


}
