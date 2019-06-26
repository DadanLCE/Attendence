package com.attend.dream.domain;

import lombok.Data;

import java.util.Date;
@Data
public class Pay {
    private int id;
    private String empCode;
    private String name;
    private int salary;
    private Date starDay;
    private Date endDay;
}
