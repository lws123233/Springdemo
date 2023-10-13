package com.example.springdemo.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisTempService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisTempService.class);

    @Autowired
    RedisTemplate redisTemplate;


}
