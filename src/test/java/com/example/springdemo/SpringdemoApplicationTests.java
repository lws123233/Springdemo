package com.example.springdemo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringdemoApplicationTests {

    @Test
    void contextLoads() {
        String a=new String("aa");
        String b=new String("aa");
        System.out.println(a.equals(b));
        System.out.println(a==b);
    }

}
