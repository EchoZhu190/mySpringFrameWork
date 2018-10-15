package com.josie;

import com.josie.annotation.ComponentScan;
import com.josie.context.BeanFactory;
import com.josie.service.UserService;

/**
 * @Author：Josie Zhu
 * @Description：
 * @Date：Create in 14:40 2018/10/8
 */
@ComponentScan(basePackage = "com.josie.config")
public class RunApplication {
    public static void  main (String [] args){
        //初始化容器
        BeanFactory beanFactory =  BeanFactory.newInstance(RunApplication.class);
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.div(1,0);
    }
}
