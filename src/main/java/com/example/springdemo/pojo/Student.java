package com.example.springdemo.pojo;

import lombok.Data;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
/**
 * 这个生命周期展示还差Aware类接口，这类回调主要发生在属性填充后，before之前。也是用于给bean内部提供某些对象的
 */

@Lazy
@Component
public class Student implements InitializingBean{

    String id;

    public String getId() {
        return id;
    }

    @Value("123")
    public void setId(String id) {
        System.out.println("属性填充");
        this.id = id;
    }

    public Student(){
        System.out.println("构造方法调用"+id);
    }
    @PostConstruct
    public void init(){
        System.out.println("初始化方法调用"+ id);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("属性设置之后方法调用"+id);
    }


}
