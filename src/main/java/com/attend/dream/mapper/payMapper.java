package com.attend.dream.mapper;

import com.attend.dream.domain.Pay;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface payMapper {

    @Select("select id, empCode, name, salary, starDay, endDay " +
            "from pay where empCode LIKE CONCAT('%',#{empCode},'%') ")
    List<Pay> getPayByCode(String empCode);

    @Insert("insert into pay(empCode, name, salary, starDay, endDay) " +
            "values(#{empCode}, #{name}, #{salary}, #{starDay}, #{endDay}) ")
    Boolean insertPay(Pay pay);
}
