package com.attend.dream.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

/*
 * @description: 用户类
 * */

@Data
public class User {

    private int id;
    private String userName;
    private String password;
    private String userCode;

}
