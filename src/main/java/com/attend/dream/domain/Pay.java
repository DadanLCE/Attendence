package com.attend.dream.domain;

import lombok.Data;

import java.util.Date;

/*
* @description: 指派薪水实体类
* */

@Data
public class Pay {

    private int id;
    private String empCode;
    private String name;
    private int salary;
    private Date startDay;
    private Date endDay;
}
