package com.attend.dream.domain;

import lombok.Data;

import java.util.Date;
@Data
public class Pay {

    private int id;
    //员工编码
    private String empCode;
    //名字
    private String name;
    private int salary;
    private Date startDay;
    private Date endDay;
}
