package com.example.springdemo.Test;

import com.example.springdemo.pojo.Student;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class MyBeanPostProcess implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if(bean instanceof Student){
            System.out.println("postProcessBefore"+bean);
        }
        return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if(bean instanceof Student){
            System.out.println("postProcessAfter"+bean);
        }
        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }
}
