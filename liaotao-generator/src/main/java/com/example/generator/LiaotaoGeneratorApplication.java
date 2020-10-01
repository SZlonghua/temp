package com.example.generator;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "com.example.generator.*.mapper")
public class LiaotaoGeneratorApplication {

    public static void main(String[] args) {
        SpringApplication.run(LiaotaoGeneratorApplication.class, args);
    }

}
