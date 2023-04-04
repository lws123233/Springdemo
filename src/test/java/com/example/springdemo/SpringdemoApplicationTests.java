package com.example.springdemo;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CompletableFuture;
@Slf4j
@SpringBootTest
class SpringdemoApplicationTests {

    @Test
    void contextLoads() {
        String a=new String("aa");
        String b=new String("aa");
        System.out.println(a.equals(b));
        System.out.println(a==b);
    }

     @Test
    void CompletableFutureTestOne(){
        CompletableFuture<Object> objectCompletableFuture = CompletableFuture.completedFuture("good");
        try {
            Object o = objectCompletableFuture.get();
            log.info(o.toString());
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    @Test
    void CompletableFutureTestTwo(){
        //异步不带返回值
        CompletableFuture<Void> 执行了异步不带返回值1 = CompletableFuture.runAsync(new Runnable() {
            @Override
            public void run() {
                log.info("执行了异步不带返回值1");
            }
        });
        CompletableFuture<Void> 执行了异步不带返回值2 = CompletableFuture.runAsync(new Runnable() {
            @Override
            public void run() {
                log.info("执行了异步不带返回值2");
            }
        });
        try {
            log.info(执行了异步不带返回值1.get().toString());
            log.info(执行了异步不带返回值2.get().toString());
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }


}
