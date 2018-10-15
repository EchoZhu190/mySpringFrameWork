package com.josie.service.Impl;

import com.josie.service.UserService;

/**
 * @Author：Josie Zhu
 * @Description：
 * @Date：Create in 17:34 2018/9/29
 */
public class UserServiceImpl implements UserService {
    @Override
    public double  getUserName(int s) {
        System.out.println("userName:1--");
        double  x = 100 / s;
        return x;
    }

    @Override
    public double div(int s, int v) {
        double res = s/v;
        System.out.println("目标方法类执行 结果:"+res);
        return res;
    }
}
