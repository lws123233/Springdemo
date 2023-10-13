package com.example.springdemo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springdemo.pojo.Data;

import java.util.List;



public interface DataService extends IService<Data> {
    Data getData(String id);
}
