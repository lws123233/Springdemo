package com.example.springdemo.feign;

import com.example.springdemo.pojo.Data;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("service-provider-name")
public interface FeignTestService {
    @RequestMapping("/service/provider/url")
    public Data serviceProviderMethod();
}
