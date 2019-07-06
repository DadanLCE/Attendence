package com.attend.dream.domain;

import lombok.Data;

/*
 * @description: 员工类
 * */

@Data
public class Employee {

    //序号
    private int empId;
    //职工编码
    private String empCode;
    //职工姓名
    private String empName;
    //职工性别
    private String empSex;
    //职工年龄
    private String empAge;
    //职工民族
    private String empNation;
    //职工身份证号
    private String empIdCard;
    //职工工资
    private int empSalary;
    //职工电话
    private String empTel;
    //紧急联系人
    private String empContact;
    //紧急联系人电话
    private String empCTel;
    //工作编号
    private String empStaCode;
    //简介
    private String empNote;

}
