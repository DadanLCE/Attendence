package com.attend.dream.domain;

import lombok.Data;

@Data
public class Employee {

    private int empId;//序号
    private String empCode;//职工编码
    private String empName;//职工姓名
    private String empSex;//职工性别
    private String empAge;//职工年龄
    private String empNation;//职工民族
    private String empIdCard;//职工身份证号
    private int  empSalary;//职工工资
    private String empTel;//职工电话
    private String empContact;//紧急联系人
    private String empCTel;//紧急联系人电话
    private String empStaCode;//工作编号
    private String empNote;//简介

}
