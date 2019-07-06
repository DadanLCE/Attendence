package com.attend.dream.domain;

import lombok.Data;

import java.util.Date;

/*
 * @description: 补卡单实体类
 * */

@Data
public class RepairCard {

    private int id;

    private String cardCode;

    private String name;

    private Date time;

    private String note;


}
