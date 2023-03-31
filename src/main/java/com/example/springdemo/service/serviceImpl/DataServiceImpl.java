package com.example.springdemo.service.serviceImpl;

import com.example.springdemo.mapper.DataMapper;
import com.example.springdemo.pojo.Data;
import com.example.springdemo.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DataServiceImpl implements DataService {

    @Autowired
    private DataMapper dataMapper;

    @Value("${name}")
    private String name;

    @Override
    public Data getData(String id) {
        return dataMapper.getData(id);
    }
}
