package com.josie.interceptor;

import com.josie.config.MyAspect;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Author：Josie Zhu
 * @Description：
 * @Date：Create in 17:11 2018/10/8
 */
public class AfterThrowingAdvice extends Interceptor{
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Exception {
        Object obj=null;
        try{
            obj=methodProxy.invoke(o,objects);
            return obj;
        }catch (Exception e){
            excuteAfterThrowMethod();
            throw new Exception(e.getMessage());
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return null;
    }
    public void excuteAfterThrowMethod(){
        MyAspect myAdvice = new MyAspect();
        try {
            myAdvice.getAfterThrowing();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}
