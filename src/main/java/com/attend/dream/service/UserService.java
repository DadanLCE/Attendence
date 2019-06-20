package com.attend.dream.service;

import com.attend.dream.domain.User;
import com.attend.dream.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {


    @Autowired(required = false)
    UserMapper userMapper;

    public User findUserByUsername(String username) {

        User user = userMapper.findUserByusername(username);

        return user;
    }
}
