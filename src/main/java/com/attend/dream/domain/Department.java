package com.attend.dream.domain;

import lombok.Data;

@Data
public class Department {

    private int id;//序号

    private String depCode;//部门编码

    private String depJob;//部门职责

    private String depName;//部门名称

    private String depBoss;//部门负责人

    private String TopId;//上级部门ID
}
