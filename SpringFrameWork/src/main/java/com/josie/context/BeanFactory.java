package com.josie.context;

import com.josie.annotation.Bean;
import com.josie.annotation.ComponentScan;
import com.josie.annotation.Configuration;
import com.josie.annotation.aop.*;
import com.josie.annotation.enable.EnableAspcetProxy;
import com.josie.annotation.ioc.Scope;
import com.josie.config.BeanDefinition;
import com.josie.interceptor.*;
import com.josie.proxy.cglib.ProxyInvocation;
import org.reflections.Reflections;

import java.lang.annotation.Annotation;
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
    private   Map<String, BeanDefinition> registry = new HashMap();
    private   Class clazz;
    private static BeanFactory beanFactory;

    public static  BeanFactory newInstance(Class clazz){
        if(beanFactory==null){
             beanFactory = new BeanFactory(clazz);
             beanFactory.init();
        }
        return beanFactory;
    }
    private  BeanFactory(Class clazz) {
        this.clazz = clazz;
    }
    private void init(){
        String path  = this.clazz.getPackage().getName();
        if(this.clazz.isAnnotationPresent(ComponentScan.class)){
            ComponentScan componentScan = (ComponentScan) this.clazz.getAnnotation(ComponentScan.class);
            path=componentScan.basePackage();
        }

        try {
           scanAspect(path);
           scanConfiguration(path);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
     public Object createProxyBean(Class clazz, Method method) throws IllegalAccessException, InstantiationException, InvocationTargetException {
         //源对象
         Bean bean = method.getAnnotation(Bean.class);
         Object invoke = method.invoke(clazz.newInstance());
         String repackge = invoke.getClass().getPackage().getName();
         ProxyInvocation vocation = new ProxyInvocation();
         for(Annotation annotation:clazz.getAnnotations()){
             //如果启用切面注解类
             if (annotation.annotationType() == EnableAspcetProxy.class){
                 //获取拦截器链 并注入到代理类中
                 vocation.setInterceptors(getInterceptors(repackge));
             }
         }
         //代理对象
         Object object = vocation.getProxy(invoke.getClass());
       return object;
     }
     //Map<Method,List<Interceptor>> methodInterceptorsListMap =new HashMap<>();

     public void creatBean(Class clazz,Method method) throws IllegalAccessException, InstantiationException, InvocationTargetException {
         //源对象
         Bean beanA = method.getAnnotation(Bean.class);

         Object bean = method.invoke(clazz.newInstance());
         BeanDefinition beanDefinition = new BeanDefinition();
         if(method.isAnnotationPresent(Scope.class)){
             Scope scope = method.getAnnotation(Scope.class);
             if(scope.value().equals("prototype")){
                 beanDefinition.setPrototype(true);
             }
         }
         Annotation [] annotations = clazz.getAnnotations();
         if(annotations.length>1){
             //创建代理增强类
             bean = createProxyBean(clazz,method);
         }
         beanDefinition.setBean(bean);
         registry.put(beanA.value(),beanDefinition);
     }

     public List<Interceptor>  getInterceptors(String repackge){
         List<Interceptor> interceptorList = new ArrayList<>();
         //判断当前类所在的包是否是当前通知类所作用下的包及子包
         if(this.advicePackageMap.get("beforeAdvice")!=null&&repackge.contains(this.advicePackageMap.get("beforeAdvice"))){
             interceptorList.add(new BeforeAdvice());
         }
         if(this.advicePackageMap.get("afterAdvice")!=null&&repackge.contains(this.advicePackageMap.get("afterAdvice"))){
             interceptorList.add(new AfterAdvice());
         }

         if(this.advicePackageMap.get("afterReturningAdvice")!=null&&repackge.contains(this.advicePackageMap.get("afterReturningAdvice"))){
             interceptorList.add(new AfterReturningAdvice());
         }
         if(this.advicePackageMap.get("afterThrowingAdvice")!=null&&repackge.contains(this.advicePackageMap.get("afterThrowingAdvice"))){
             interceptorList.add(new AfterThrowingAdvice());
         }
         if(this.advicePackageMap.get("aroundAdvice")!=null&&repackge.contains(this.advicePackageMap.get("aroundAdvice"))){
             interceptorList.add(new AroundAdvice());
         }

         return interceptorList;
     }

     public void scanConfiguration(String path) throws IllegalAccessException, InstantiationException, InvocationTargetException {
         Reflections reflections = new Reflections(path);
         Set<Class<?>> typesAnnotated= reflections.getTypesAnnotatedWith(Configuration.class);
         for (Class clazz : typesAnnotated) {
             Method[] declaredMethods = clazz.getDeclaredMethods();
             for (Method method : declaredMethods) {
                 if(method.isAnnotationPresent(Scope.class)){
                     creatBean(clazz,method);
                 }
             }
         }
     }
    //存放advice 以及其作用的包
    private Map<String,String> advicePackageMap = new HashMap();
    private  void scanAspect(String path) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        Reflections reflections = new Reflections(path);
        Set<Class<?>> typesAnnotatedWith = reflections.getTypesAnnotatedWith(Aspect.class);
        for (Class clazz : typesAnnotatedWith) {
            Method[] declaredMethods = clazz.getDeclaredMethods();
            for (Method method : declaredMethods) {
                if(method.isAnnotationPresent(Before.class)){
                    Before beforeAdvice = method.getAnnotation(Before.class);
                    advicePackageMap.put("beforeAdvice",beforeAdvice.value());
                }
                if(method.isAnnotationPresent(Around.class)){
                    Around aroundAdvice = method.getAnnotation(Around.class);
                    advicePackageMap.put("aroundAdvice",aroundAdvice.value());
                }
                if(method.isAnnotationPresent(After.class)){
                    After afterAdvice = method.getAnnotation(After.class);
                    advicePackageMap.put("afterAdvice",afterAdvice.value());
                }
                if(method.isAnnotationPresent(AfterReturning.class)){
                    AfterReturning afterReturning = method.getAnnotation(AfterReturning.class);
                    advicePackageMap.put("afterReturningAdvice", afterReturning.value());
                }


                if(method.isAnnotationPresent(AfterThrowing.class)){
                    AfterThrowing afterThrowing = method.getAnnotation(AfterThrowing.class);
                    advicePackageMap.put("afterThrowingAdvice", afterThrowing.value());
                }

            }
        }
    }

    public  Object getBean(String  name){
        BeanDefinition beanDefinition =  registry.get(name);
       /*if(beanDefinition!=null&&beanDefinition.getPrototype()){
           creatBean();
        }*/
        Object bean = beanDefinition.getBean();
       return  bean;
    }
}
