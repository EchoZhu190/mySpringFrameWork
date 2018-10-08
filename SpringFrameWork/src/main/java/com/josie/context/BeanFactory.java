package com.josie.context;

import com.josie.annotation.Bean;
import com.josie.annotation.Configuration;
import com.josie.annotation.aop.After;
import com.josie.annotation.aop.Around;
import com.josie.annotation.aop.Aspect;
import com.josie.annotation.aop.Before;
import com.josie.config.BeanDefinition;
import com.josie.interceptor.AfterAdvice;
import com.josie.interceptor.AroundAdvice;
import com.josie.interceptor.BeforeAdvice;
import com.josie.interceptor.Interceptor;
import com.josie.proxy.cglib.ProxyInvocation;
import com.josie.service.UserService;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @Author：Josie Zhu
 * @Description：
 * @Date：Create in 16:30 2018/9/29
 */
public class BeanFactory {
    /**
     * 注册bean容器
     */
    public  Map<String, BeanDefinition> registry = new HashMap();
     //注入componentScan 包扫描
     public void scanConfiguration(String path) throws IllegalAccessException, InstantiationException, InvocationTargetException {
         Reflections reflections = new Reflections(path);
         Set<Class<?>> typesAnnotatedWith = reflections.getTypesAnnotatedWith(Configuration.class);
         for (Class clazz : typesAnnotatedWith) {
             Method[] declaredMethods = clazz.getDeclaredMethods();
             for (Method method : declaredMethods) {
                 Bean bean = method.getAnnotation(Bean.class);
                 if (bean != null) {
                     Object invoke = method.invoke(clazz.newInstance());//源对象
                     String repackge = invoke.getClass().getPackage().getName();
                     if(repackge.contains(this.map.get("beforeAdvice").toString())){//前置通知范围内的包，创建为proxy对象
                         interceptorList.add(new BeforeAdvice());
                     }
                     if(repackge.contains(this.map.get("afterAdvice").toString())){//前置通知范围内的包，创建为proxy对象
                         interceptorList.add(new AfterAdvice());
                     }
                     if(repackge.contains(this.map.get("aroundAdvice").toString())){//前置通知范围内的包，创建为proxy对象
                         interceptorList.add(new AroundAdvice());
                     }
                     ProxyInvocation actionInvocation = new ProxyInvocation();
                     actionInvocation.setInterceptors(interceptorList);
                     Object object = actionInvocation.getProxy(invoke.getClass());//代理对象
                     BeanDefinition beanDefinition = new BeanDefinition();
                     beanDefinition.setBean(object);
                     registry.put(bean.value(),beanDefinition);
                 }
             }
         }
     }
    private Map map = new HashMap();
    List<Interceptor> interceptorList = new ArrayList<>();
    public void scanAspect(String path) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        Reflections reflections = new Reflections(path);
        Set<Class<?>> typesAnnotatedWith = reflections.getTypesAnnotatedWith(Aspect.class);
        for (Class clazz : typesAnnotatedWith) {
            Method[] declaredMethods = clazz.getDeclaredMethods();
            for (Method method : declaredMethods) {
                if(method.isAnnotationPresent(Before.class)){
                    Before beforeAdvice = method.getAnnotation(Before.class);
                    map.put("beforeAdvice",beforeAdvice.value());
                }
                if(method.isAnnotationPresent(After.class)){
                    After afterAdvice = method.getAnnotation(After.class);
                    map.put("afterAdvice",afterAdvice.value());
                }
                if(method.isAnnotationPresent(Around.class)){
                    Around aroundAdvice = method.getAnnotation(Around.class);
                    map.put("aroundAdvice",aroundAdvice.value());
                }
            }
        }
    }

    public  Object getBean(String name){
        BeanDefinition beanDefinition =  registry.get(name);
        Object bean = beanDefinition.getBean();
       return  bean;
    }

    public static void main(String [] args){

        BeanFactory beanFactory = new BeanFactory();
        try {
            beanFactory.scanAspect("com.josie.config");
            beanFactory.scanConfiguration("com.josie.config");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.getUserName();
    }
}
