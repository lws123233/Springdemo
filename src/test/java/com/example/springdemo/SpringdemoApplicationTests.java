package com.example.springdemo;

import com.example.springdemo.pojo.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
@Slf4j
@SpringBootTest
class SpringdemoApplicationTests {

    @Autowired
    RedisTemplate redisTemplate;


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


    @Test
    void reidsTest(){
        Data data1=new Data("1","2","3","4");
        Data data2=new Data("1a","2a","3a","4a");
        Data data3=new Data("1b","2b","3b","4b");
        List<Data> dataList=new ArrayList<>();
        dataList.add(data1);
        dataList.add(data2);
        dataList.add(data3);
        System.out.println(redisTemplate.opsForList().range("test",1,2));

        redisTemplate.delete("test");

    }
}
