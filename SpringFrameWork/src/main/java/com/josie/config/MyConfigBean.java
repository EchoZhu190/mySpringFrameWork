package com.josie.config;

import com.josie.annotation.Bean;
import com.josie.annotation.Configuration;
import com.josie.annotation.enable.EnableAspcetProxy;
import com.josie.annotation.ioc.Scope;
import com.josie.service.Impl.UserServiceImpl;
import com.josie.service.UserService;

/**
 * @Author：Josie Zhu
 * @Description：
 * @Date：Create in 17:30 2018/9/29
 */
@EnableAspcetProxy
@Configuration
public class MyConfigBean {
    @Scope("prototype")
    @Bean("userService")
    public UserService userService(){
        return new UserServiceImpl();
    }

    @Bean("aspect")
    public MyAspect userAspectInstance(){
        return new MyAspect();
    }
}
