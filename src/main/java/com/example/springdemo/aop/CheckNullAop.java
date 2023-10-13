package com.example.springdemo.aop;

import com.example.springdemo.pojo.Data;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.List;

@Aspect//切面
@Component
@Slf4j
public class CheckNullAop {

    //连接点
    @Pointcut("@annotation(com.example.springdemo.aop.CheckNull)")
    public void myCheckNull(){
    }

    //通知
    @Before(value = "myCheckNull()")
    public void before(JoinPoint joinPoint) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchFieldException {
        log.info("进入了Before");
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        //获取目标方法所在的类实列对象
        Object target = joinPoint.getTarget();
        //获取到当前执行的方法对象
        Method method = target.getClass().getMethod(methodSignature.getName(), methodSignature.getParameterTypes());
        //方法对象可以获取方法上的注解对象列表
        CheckNull annotation = method.getAnnotation(CheckNull.class);
        String value = annotation.value();
        //获取方法参数的对象（类型和值）列表
        Object[] parameterObjArray = joinPoint.getArgs();
        //获取方法参数对象列表（类型）
        Parameter[] parameters = method.getParameters();
        //前置处理器也可以执行方法，但其执行结果与目标方法的结果互不影响
       // Object invoke = method.invoke(target, parameterObjArray);
       // log.info(invoke.toString());

        //参数列表对象
        for(Object parameterObj : parameterObjArray){
            if(parameterObj instanceof Data){
                //获取对象指定属性
                Field declaredField = parameterObj.getClass().getDeclaredField(value);
                declaredField.setAccessible(true);
                Integer attributeObjInParameterObj =Integer.parseInt( declaredField.get(parameterObj).toString() );
                attributeObjInParameterObj++;
                declaredField.set(parameterObj,attributeObjInParameterObj.toString());
            }
        }

      //  Object invoke1 = method.invoke(target, parameterObjArray);
       // log.info(invoke1.toString());

    }

    @Around(value = "myCheckNull()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        log.info("进入了around");
        Signature signature = proceedingJoinPoint.getSignature();

        StopWatch stopWatch=new StopWatch();
        stopWatch.start();
        log.info("around执行前");
        Object result = proceedingJoinPoint.proceed();
        log.info("around执行后");
        stopWatch.stop();

        log.info(signature.getDeclaringTypeName()+"  "+ signature.getDeclaringType() +"  "+ signature.getName()+"  "+stopWatch.getTotalTimeMillis());

        return result;
    }


    /**
     * 可以用来防止接口被重复请求
     */
    @AfterReturning(pointcut = "myCheckNull()", returning = "res")
    public void afterReturning(JoinPoint joinPoint, Object res) {
        log.info("进入了afterReturning");
        if(res instanceof Data){

            Data data=(Data) res;
            data.setId(data.getId()+"after");
            log.info(data.getId());
        }
    }

    @After(value ="myCheckNull()")
    public void after(JoinPoint joinPoint){
        log.info("进入了after");
    }

}
