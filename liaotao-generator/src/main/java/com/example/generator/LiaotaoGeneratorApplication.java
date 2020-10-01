package com.example.generator;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan(value = "com.example.generator.*.mapper")
@ComponentScan(basePackages = {"com.example.generator","com.example.commom"})
public class LiaotaoGeneratorApplication {

    public static void main(String[] args) {
        SpringApplication.run(LiaotaoGeneratorApplication.class, args);
    }

}
