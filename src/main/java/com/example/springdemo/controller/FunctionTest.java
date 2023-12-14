package com.example.springdemo.controller;

import com.example.springdemo.pojo.Data;

/**
 * 函数式接口测试，functionTest1，functionTest1相当于FunctionTest的两个实现。
 * 这种写法可以不用写实现类而进行接口的实现
 * 注意这种接口只能有一个抽象方法
 */
public interface FunctionTest {
    FunctionTest functionTest1=(var)->{
        System.out.println("这是函数1");
    };

    FunctionTest functionTest2=(var)->{
        System.out.println("这是函数2");
    };


    FunctionTest choose=(var)->{
        switch (var){
            case "functionTest1" : functionTest1.create(var);break;
            case "functionTest2" : functionTest2.create(var);break;
            case "functionTest3" : functionTest3(var);break;
            default: System.out.println("未知函数");;
        }
    };

    void create(String id);


    static void functionTest3(String var){
        System.out.println("这是函数3");
    }


}
