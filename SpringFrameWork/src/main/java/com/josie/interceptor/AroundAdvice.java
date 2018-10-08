package com.josie.interceptor;

import com.josie.config.MyAdvice;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by xiaoqin on 2018/10/6.
 */
public class AroundAdvice extends Interceptor {

    private Object target ;
    private Object[] objects;
    private MethodProxy methodProxy;

    public Object getTarget() {
        return target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }

    public Object[] getObjects() {
        return objects;
    }

    public void setObjects(Object[] objects) {
        this.objects = objects;
    }

    public MethodProxy getMethodProxy() {
        return methodProxy;
    }

    public void setMethodProxy(MethodProxy methodProxy) {
        this.methodProxy = methodProxy;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        AroundAdvice point = new AroundAdvice();
       // methodProxy.invoke(o,objects);
        point.setMethodProxy(methodProxy);
        point.setObjects(objects);
        point.setTarget(o);
        excuteAroundMethod(point);
        return null;
    }
    public void excuteAroundMethod(AroundAdvice aroundAdvice){
        MyAdvice myAdvice = new MyAdvice();
        try {
            myAdvice.getAround(aroundAdvice);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}
