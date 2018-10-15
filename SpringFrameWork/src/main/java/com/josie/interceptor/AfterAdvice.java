package com.josie.interceptor;

import com.josie.config.MyAspect;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;


public class AfterAdvice extends Interceptor {
	@Override
	public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
		Object obj=null;
		try{
			obj= methodProxy.invoke(o,objects);
		}catch (Exception e){
			System.out.println("异常情况");
		}
		finally {
			excuteAfterMethod();
		}

		return obj;
	}
	public void excuteAfterMethod(){
		MyAspect myAdvice = new MyAspect();
		myAdvice.getAfter();
	}
}
