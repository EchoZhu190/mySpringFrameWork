package com.josie.config;

import com.josie.annotation.aop.*;
import com.josie.proxy.cglib.ProceedingJoinPoint;

/**
 * Created by xiaoqin on 2018/10/5.
 */
@Aspect
public class MyAspect {
    @After("com.josie.service")
    public void getAfter(){
        System.out.println("后置通知after");
    }

    @Before("com.josie.service")
    public void getBefore(){
        System.out.println("前置通知before");
    }

    @Around("com.josie.service")
    public Object getAround(ProceedingJoinPoint p) throws Throwable,Exception {
        System.out.println("环绕-前置");
        Object obj= p.proceed();
        System.out.println("环绕-后置");
        return obj;
    }
    @AfterReturning("com.josie.service")
    public void getAfterReturning(Object obj) throws Throwable {
        System.out.println("正常返回通知afterReturning"+obj);
    }
    @AfterThrowing("com.josie.service")
    public void getAfterThrowing() throws Throwable {
        System.out.println("异常返回通知afterThrowing");
    }
}
