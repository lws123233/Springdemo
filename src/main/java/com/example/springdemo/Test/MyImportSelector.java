package com.example.springdemo.Test;


import com.example.springdemo.interceotor.InterceptorProperty;
import com.example.springdemo.pojo.Student;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.boot.autoconfigure.AutoConfigurationPackages;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * 通过import注解导入这个类后，可在这个类中集中注入一些bean对象
 * 实现ImportSelector或者ImportBeanDefinitionRegistrar都行，这个为了演示都实现了，都实现会使ImportBeanDefinitionRegistrar失效
 * 实现FactoryBean接口则只能注入T类型的bean
 * 实现BeanDefinitionRegistryPostProcessor可以往beanDefinitionMap里添加bean，它属于beanFactoryPostProcess，先于beanFactoryPostProcess
 *
 */
public class MyImportSelector implements ImportSelector, ImportBeanDefinitionRegistrar , FactoryBean<Student> , BeanDefinitionRegistryPostProcessor {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{InterceptorProperty.class.getName()};
    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        RootBeanDefinition rootBeanDefinition=new RootBeanDefinition(InterceptorProperty.class);
        registry.registerBeanDefinition("MyTestSpring",rootBeanDefinition);
    }

    @Override
    public Student getObject() throws Exception {
        return new Student();
    }

    @Override
    public Class<?> getObjectType() {
        return Student.class;
    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {

    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {

    }
}
