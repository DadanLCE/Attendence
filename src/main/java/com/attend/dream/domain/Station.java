package com.attend.dream.domain;


import lombok.Data;

@Data
public class Station {

    private int id;//序号

    private String staCode;//岗位编码

    private String staName;//岗位名称

    private String staDep;//所在部门

    private String staBoss;//直接上级

    private String staCate;//岗位类别

    private String staDes;//岗位职责描述
}
