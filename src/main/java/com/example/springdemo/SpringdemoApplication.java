package com.example.springdemo;

import com.baomidou.mybatisplus.core.mapper.Mapper;
import com.example.springdemo.aop.EnableJWTAutoConfiguration;
import com.example.springdemo.pojo.Student;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.context.environment.EnvironmentChangeEvent;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@EnableFeignClients(basePackages = "com.example.springdemo.feign")
@SpringBootApplication
@EnableJWTAutoConfiguration
public class SpringdemoApplication {

    public static void main(String[] args) throws InterruptedException {
        //
        ConfigurableApplicationContext run = SpringApplication.run(SpringdemoApplication.class, args);
//        for (String beanDefinitionName : run.getBeanDefinitionNames()) {
//            System.out.println(beanDefinitionName);
//        }
        run.getBean(Student.class);


    }

}
