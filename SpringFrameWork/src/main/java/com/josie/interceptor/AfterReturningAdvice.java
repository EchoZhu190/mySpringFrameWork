package com.josie.interceptor;

import com.josie.config.MyAspect;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Author：Josie Zhu
 * @Description：
 * @Date：Create in 17:11 2018/10/8
 */
public class AfterReturningAdvice extends Interceptor{
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Exception {
        Object obj=null;
        try{
            obj=methodProxy.invoke(o,objects);
            excuteAfterReMethod(obj);
        }catch (Exception e){
            System.out.println("异常情况");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return obj;
    }
    public void excuteAfterReMethod(Object obj){
        MyAspect myAdvice = new MyAspect();
        try {
            myAdvice.getAfterReturning(obj);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}
