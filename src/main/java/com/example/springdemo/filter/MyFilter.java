package com.example.springdemo.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

@Component
@Slf4j
public class MyFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("执行了过滤器MyFilter前");
        filterChain.doFilter(servletRequest,servletResponse);
        log.info("执行了过滤器MyFilter后");
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
