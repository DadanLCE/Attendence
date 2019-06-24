package com.attend.dream.domain;

import lombok.Data;

@Data
public class Classes {
    private int id;

    //编码
    private String claCode;
    //名称
    private String claName;
    //早上上班时间
    private String claMorTime;
    //下午下班时间
    private String claEveTime;
    //备注
    private String claNote;
}
