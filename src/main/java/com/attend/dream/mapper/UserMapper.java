package com.attend.dream.mapper;

import com.attend.dream.domain.User;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    @Select("select * from login where userName=#{userName}")
    User findUserByUsername(String userName);

    @Select("select userName from login")
    String[] getAlluserName();

}
