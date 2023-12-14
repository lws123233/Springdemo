package com.example.springdemo.aop;

import com.example.springdemo.Test.MyImportSelector;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.METHOD,ElementType.TYPE})
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Import(MyImportSelector.class)
public @interface EnableJWTAutoConfiguration {
}
