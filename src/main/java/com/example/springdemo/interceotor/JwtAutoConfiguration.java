package com.example.springdemo.interceotor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@RefreshScope
@Configuration
public class JwtAutoConfiguration implements WebMvcConfigurer {
    @Autowired
    InterceptorProperty interceptorProperty;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        WebMvcConfigurer.super.addInterceptors(registry);
        registry.addWebRequestInterceptor(new MyInterceptor())
                .addPathPatterns(interceptorProperty.getUrlPatterns())
                .excludePathPatterns(interceptorProperty.getExcludeUrl());
    }
}
