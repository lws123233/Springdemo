package com.example.springdemo.mapper;

import com.example.springdemo.pojo.Data;
import org.apache.ibatis.annotations.Select;

import java.util.List;


public interface DataMapper {
    @Select("select * from data where id=#{id}")
    Data getData(String id);
}
