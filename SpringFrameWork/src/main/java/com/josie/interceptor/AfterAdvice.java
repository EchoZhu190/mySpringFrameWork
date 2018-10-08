package com.josie.interceptor;

import com.josie.config.MyAdvice;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class AfterAdvice extends Interceptor {
	@Override
	public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
		methodProxy.invoke(o,objects);
		excuteAfterMethod();
		return null;
	}
	public void excuteAfterMethod(){
		MyAdvice myAdvice = new MyAdvice();
		myAdvice.getAfter();
	}
}
