package com.attend.dream.mapper;

import com.attend.dream.domain.CardEve;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.Date;
import java.util.List;

public interface CardEveMapper {



    //通过id来查询 打卡单
    @Select("select id, cardCode, name, date, note " +
            "from cardEve where id = #{id} and flag = 'true' ")
    CardEve getCardEveById(int id);

//    //用于结算当天的出勤情况
//    @Select("select id,cardCode,name,date,note" +
//            "from card where cardCode = #{cardCode}")
//    List<Card> getCardsToday(String cardCode);


//    在打卡单  通过cardCode的 模糊查询
    @Select("select id, cardCode, name, date , note  " +
            "from cardEve where cardCode like concat('%',#{cardCode},'%') " +
            " and flag = 'true'")
    List<CardEve> getCardEvesByCode(String cardCode);

    //通过id来查询 打卡单
    @Select("select id, cardCode, name, date, note " +
            "from cardEve where id = #{id} and flag = 'false' ")
    CardEve getCardEveByIdFlag(int id);

    //    在补卡单  通过cardCode的 模糊查询
    @Select("select id, cardCode, name, date , note  " +
            "from cardEve where cardCode like concat('%',#{cardCode},'%') " +
            " and flag = 'false'")
    List<CardEve> getCardEvesByCodeFlag(String cardCode);



    @Select("SELECT id,cardCode,date FROM cardEve where cardCode like concat('%',#{cardCode},'%')\n" +
            "UNION\n" +
            "SELECT id,cardCode,date FROM cardMor where cardCode like concat('%',#{cardCode},'%')")
    List<CardEve> getCardAllByCode(String cardCode);

    //插入数据
    @Insert("insert into cardEve (cardCode, name, date ,note, flag ) " +
            "values(#{cardCode}, #{name}, #{date}, #{note}), flag=#{flag} ")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    Boolean insertCardEve(CardEve card);



    //更新数据 补卡单
    @Update("update cardEve set cardCode=#{cardCode}, name=#{name}, date=#{date}, note=#{note} " +
            "where id = #{id} ")
    Boolean updateCardFlag(CardEve card);

    //删除数据 补卡单
    @Delete("delete from cardEve where id=#{id}")
    Boolean deleteCard(int id);


    @Select("select id, cardCode, name, date , note, cardCodeMor " +
            "from cardEve where cardCode like concat('%',#{cardCode},'%') " +
            "and date between #{preDate} and #{nextDate}")
    @Results({
            @Result(id=true, column = "id", property = "id"),
            @Result(column = "cardCode", property = "cardCode"),
            @Result(column = "name", property = "name"),
            @Result(column = "date", property = "date"),
            @Result(column = "note", property = "note"),
//            @Result(column = "cardCodeMor", property = "cardMor", one = @One(select = "com.attend.dream.mapper.getCardMorByCode",fetchType = FetchType.EAGER))
            @Result(column = "cardCodeMor", property = "cardMor", one = @One(select = "com.attend.dream.mapper.CardMorMapper.getCardMorByCode",fetchType = FetchType.EAGER))
    })
    List<CardEve> getAll(@Param("cardCode")String cardCode, @Param("preDate") Date preDate, @Param("nextDate") Date nextDate);



}
