package com.example.springdemo.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
@Component
@Slf4j
public class MyFilter2 extends GenericFilterBean {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("执行了过滤器MyFilter2前");
        filterChain.doFilter(servletRequest,servletResponse);
        log.info("执行了过滤器MyFilter2后");
    }
}
