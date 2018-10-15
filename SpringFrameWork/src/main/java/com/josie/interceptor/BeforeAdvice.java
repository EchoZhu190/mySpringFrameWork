package com.josie.interceptor;

import com.josie.config.MyAspect;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class BeforeAdvice extends Interceptor{

	@Override
	public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
		Object obj;
		excuteBeforeMethod();
		obj=methodProxy.invoke(o,objects);
		return obj;
	}
	 public void excuteBeforeMethod(){
		MyAspect myAdvice = new MyAspect();
		myAdvice.getBefore();
	}
}
