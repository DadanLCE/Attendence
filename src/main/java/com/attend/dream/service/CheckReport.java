package com.attend.dream.service;

import com.attend.dream.mapper.PunchCardMapper;
import com.attend.dream.mapper.RepairCardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CheckReport {

    @Autowired(required = false)
    PunchCardMapper punchCardMapper;
    @Autowired(required = false)
    RepairCardMapper repairCardMapper;
}
