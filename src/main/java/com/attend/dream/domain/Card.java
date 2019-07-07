package com.attend.dream.domain;


import lombok.Data;

import java.util.Date;

/*
 * @description: 打卡实体类
 * */

@Data
public class Card {

    private int id;

    private String cardCode;

    private String name;

    private Date morTime;

    private Date eveTime;

    private String note;


}
