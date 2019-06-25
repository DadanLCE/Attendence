package com.attend.dream.mapper;

import com.attend.dream.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("select * from login where userName=#{userName}")
    User findUserByUsername(String userName);

}
