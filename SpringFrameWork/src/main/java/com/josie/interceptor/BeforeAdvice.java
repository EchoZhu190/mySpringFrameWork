package com.josie.interceptor;

import com.josie.config.MyAdvice;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class BeforeAdvice extends Interceptor{

	@Override
	public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
		excuteBeforeMethod();
		methodProxy.invoke(o,objects);
		return null;
	}
	 public void excuteBeforeMethod(){
		MyAdvice myAdvice = new MyAdvice();
		myAdvice.getBefore();
	}
}
