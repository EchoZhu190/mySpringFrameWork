package com.josie.config;

import com.josie.annotation.Bean;
import com.josie.annotation.Configuration;
import com.josie.service.Impl.UserServiceImpl;
import com.josie.service.UserService;

/**
 * @Author：Josie Zhu
 * @Description：
 * @Date：Create in 17:30 2018/9/29
 */
@Configuration
public class MyConfigBean {
    @Bean("userService")
     public UserService userService(){
        return new UserServiceImpl();
    }
}
