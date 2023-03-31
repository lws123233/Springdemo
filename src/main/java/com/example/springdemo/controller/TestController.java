package com.example.springdemo.controller;


import com.example.springdemo.util.ExecutorUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RefreshScope
@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    @Value("${name}")
    private String name;

    @Autowired
    @Value("${testname}")
    private String testname;

    @GetMapping("/testRefresh")
    public void testRefresh() throws InterruptedException {

           log.info("测试1"+name);
           log.info("测试2"+testname);
           Thread.sleep(5000);

    }

    @GetMapping("/testExecutor")
    public void testExecutor() {
        log.info("testExecutor-----------------start");
        new ExecutorUtil("executorTest");
        log.info("testExecutor-----------------End");
    }
}
