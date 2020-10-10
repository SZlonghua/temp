package com.example.scheduler;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan(value = "com.example.scheduler.*.mapper")
@ComponentScan(basePackages = {"com.example.scheduler","com.example.commom"})
public class LiaotaoSchedulerApplication {

    public static void main(String[] args) {
        SpringApplication.run(LiaotaoSchedulerApplication.class, args);
    }

}
