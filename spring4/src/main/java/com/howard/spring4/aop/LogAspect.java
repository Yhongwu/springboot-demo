package com.howard.spring4.aop;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
@Aspect //声明一个切面
@Component //声明为spring容器
public class LogAspect {

    //@Pointcut声明@Action为切点
    @Pointcut("@annotation(com.howard.spring4.aop.Action)")
    public void annotationPointCut(){};

    //建言(advice) 处理@Pointcut定义的切点
    @After("annotationPointCut()")
    public void after(JoinPoint joinpoint) {
        MethodSignature signature =(MethodSignature)joinpoint.getSignature();
        Method method = signature.getMethod();
        Action annotation = method.getAnnotation(Action.class);
        System.out.println("注解式拦截"+annotation.name());
    }

    //建言 使用拦截规则的方式
    @Before("execution(* com.howard.spring4.aop.DemoMethodService.*(..))")
    public void before(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        System.out.println("方法规则拦截"+method.getName());
    }
}
