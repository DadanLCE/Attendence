package com.attend.dream;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = {"com.attend.dream.mapper"})
@SpringBootApplication
public class DreamApplication {

    public static void main(String[] args) {
        SpringApplication.run(DreamApplication.class, args);
    }

}
