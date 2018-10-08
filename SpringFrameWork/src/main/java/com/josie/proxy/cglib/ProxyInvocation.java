package com.josie.proxy.cglib;

import com.josie.interceptor.Interceptor;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;


public class ProxyInvocation implements MethodInterceptor {
	private List<Interceptor> interceptors = new ArrayList<Interceptor>();

	public List<Interceptor> getInterceptors() {
		return interceptors;
	}

	public void setInterceptors(List<Interceptor> interceptors) {
		this.interceptors = interceptors;
	}

	int index = -1;

	private Enhancer enhancer = new Enhancer();

	//通过Class对象获取代理对象
	public Object getProxy(Class c){
		//设置创建子类的类
		enhancer.setSuperclass(c);
		enhancer.setCallback(this);
		return enhancer.create();
	}

	@Override
	public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
		index ++;
		if(index >= this.interceptors.size()) {
			methodProxy.invokeSuper(o,objects);
		}else {
			this.interceptors.get(index).intercept(o,method,objects,methodProxy);
		}
		return null;
	}
}
