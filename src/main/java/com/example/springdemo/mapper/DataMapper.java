package com.example.springdemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springdemo.pojo.Data;
import org.apache.ibatis.annotations.Select;

import java.util.List;


public interface DataMapper extends BaseMapper<Data> {
    @Select("select * from data where id=#{id}")
    Data getData(String id);
}
