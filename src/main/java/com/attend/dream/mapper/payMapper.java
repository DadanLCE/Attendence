package com.attend.dream.mapper;

import com.attend.dream.domain.Pay;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PayMapper {

    @Select("select id, empCode, name, salary, startDay, endDay " +
            "from pay where empCode = #{empCode} ")
    List<Pay> getPaysByCode(String empCode);

    @Insert("insert into pay(empCode, name, salary, startDay, endDay) " +
            "values(#{empCode}, #{name}, #{salary}, #{startDay}, #{endDay}) ")
    Boolean insertPay(Pay pay);
}
