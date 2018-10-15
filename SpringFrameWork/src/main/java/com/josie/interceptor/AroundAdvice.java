package com.josie.interceptor;

import com.josie.config.MyAspect;
import com.josie.proxy.cglib.ProceedingJoinPoint;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by xiaoqin on 2018/10/6.
 */
public class AroundAdvice extends Interceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        ProceedingJoinPoint point = new ProceedingJoinPoint();
        point.setMethodProxy(methodProxy);
        point.setObjects(objects);
        point.setObject(o);
        return excuteAroundMethod(point);
    }
    public Object excuteAroundMethod(ProceedingJoinPoint proceedingJoinPoint) throws Exception,Throwable{
        MyAspect myAdvice = new MyAspect();
        Object obj=null;
        try {
            obj=myAdvice.getAround(proceedingJoinPoint);
            return obj;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }

    }
}
