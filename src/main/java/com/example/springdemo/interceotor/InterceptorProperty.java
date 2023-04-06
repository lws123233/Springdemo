package com.example.springdemo.interceotor;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@RefreshScope
@Data
@ConfigurationProperties(prefix = "interceptor")
public class InterceptorProperty {
    private String[] excludeUrl={};

    private String[] urlPatterns = {};
}
