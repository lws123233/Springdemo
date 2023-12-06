package com.example.springdemo.controller;

import com.example.springdemo.pojo.Data;


public interface FunctionTest {
    FunctionTest functionTest1=(var)->{
       return null!=var?new Data(var,var,var,var):new Data();
    };

    FunctionTest functionTest2=(var)->{
        System.out.println("这是函数2");
        return new Data();
    };

    Data create(String id);

}
