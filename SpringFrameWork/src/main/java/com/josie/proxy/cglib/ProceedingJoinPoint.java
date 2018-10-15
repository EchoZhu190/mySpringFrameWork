package com.josie.proxy.cglib;

import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Author：Josie Zhu
 * @Description：
 * @Date：Create in 10:41 2018/10/9
 */
public class ProceedingJoinPoint {
    private Object object;
    private Method method;
    private Object[] objects;
    private MethodProxy methodProxy;
    private Object returnObj;
    public Object  proceed() throws Throwable {
        Object obj = null;
            returnObj=this.getMethodProxy().invoke(object,objects);
            return  returnObj;
    }

    public Object getReturnObj() {
        return returnObj;
    }

    public void setReturnObj(Object returnObj) {
        this.returnObj = returnObj;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
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
}
