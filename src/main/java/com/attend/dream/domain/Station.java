package com.attend.dream.domain;


import lombok.Data;

/*
 * @description: 岗位实体类
 * */

@Data
public class Station {

    private int id;

    private String staCode;

    private String staName;

    private String staDep;

    private String staBoss;

    private String staCate;

    private String staDes;
}
