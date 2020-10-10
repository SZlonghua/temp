package com.example.commom;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.charset.Charset;

//@SpringBootTest
class LiaotaoCommomApplicationTests {

    @Test
    void contextLoads() {
        System.out.println("ERROR".getBytes(Charset.defaultCharset()).length);
    }

}
