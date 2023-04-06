package com.example.springdemo.interceotor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.WebRequestInterceptor;
@Component
@Slf4j
public class MyInterceptor implements WebRequestInterceptor {

    @Override
    public void preHandle(WebRequest request) throws Exception {
        log.info("执行了preHandle");
    }

    @Override
    public void postHandle(WebRequest request, ModelMap model) throws Exception {
        log.info("执行了postHandle");
    }

    @Override
    public void afterCompletion(WebRequest request, Exception ex) throws Exception {
        log.info("执行了afterCompletion");
    }
}
