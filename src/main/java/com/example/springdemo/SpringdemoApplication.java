package com.example.springdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@EnableFeignClients(basePackages = "com.example.springdemo.feign")
@EnableConfigurationProperties
@ConfigurationPropertiesScan(basePackages = "com.example.springdemo")
@SpringBootApplication(scanBasePackages = "com.example.springdemo")
@MapperScan("com.example.springdemo.Mapper")
public class SpringdemoApplication {

    public static void main(String[] args) throws InterruptedException {
        ConfigurableApplicationContext run = SpringApplication.run(SpringdemoApplication.class, args);
    }

}
