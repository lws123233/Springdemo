package com.example.springdemo.interceotor;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Component;
@Component
@Slf4j
@Intercepts(@Signature(type = Executor.class,
        method = "query",
        args = {MappedStatement.class,Object.class, RowBounds.class, ResultHandler.class}))
public class MybatisInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        log.info("执行了mybatis拦截器..........");
        return invocation.proceed();
    }
}
