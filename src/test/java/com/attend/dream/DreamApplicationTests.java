package com.attend.dream;

import com.attend.dream.domain.*;
import com.attend.dream.mapper.*;
import com.attend.dream.service.ClassesService;
import com.attend.dream.service.DepartmentService;

import com.attend.dream.service.StationService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@MapperScan(basePackages = {"com.attend.dream.mapper"})
public class DreamApplicationTests {


    public void test(){
        Date date = new Date();

    }
}
