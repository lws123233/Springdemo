package com.example.springdemo.controller;


import com.example.springdemo.aop.CheckNull;
import com.example.springdemo.pojo.Data;
import com.example.springdemo.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@RefreshScope
@RestController
@RequestMapping("/datarequest")
public class DataController {


    @Autowired
    DataService dataservice;

    @Value("${name}")
    private String name;

    @Autowired
    ApplicationContext applicationContext;

    @CheckNull
    @PostMapping("/getDatabyid")
    public Data getDataByid(@RequestBody Data date1, HttpServletRequest request){

        List<String> list=new ArrayList<>();
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        String name = applicationContext.getEnvironment().getProperty("name");
        Integer i=-1;

        applicationContext.getEnvironment().getProperty("interceptor.excludeUrl");
        List<String> collect = list.stream().filter(s -> null != s && !s.equals("")).collect(Collectors.toList());
        request.getMethod();
        Data data=dataservice.getData(date1.getId());
        System.out.println(data.getId()+"  "+
                           data.getName()+"  "+
                           data.getPrice());
        return data;
    }


}
