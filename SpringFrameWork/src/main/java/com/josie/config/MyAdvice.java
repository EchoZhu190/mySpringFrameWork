package com.josie.config;

import com.josie.annotation.aop.After;
import com.josie.annotation.aop.Around;
import com.josie.annotation.aop.Aspect;
import com.josie.annotation.aop.Before;
import com.josie.interceptor.AroundAdvice;

/**
 * Created by xiaoqin on 2018/10/5.
 */
@Aspect
public class MyAdvice {
    @After("com.josie.service")
    public void getAfter(){
        System.out.println("后置通知after");
    }
    @Before("com.josie.service")
    public void getBefore(){
        System.out.println("前置通知before");
    }

    @Around("com.josie.service")
    public void getAround(AroundAdvice p) throws Throwable {
        System.out.println("环绕-前置");
        p.getMethodProxy().invoke(p.getTarget(),p.getObjects());
        System.out.println("环绕-后置");
    }
}
