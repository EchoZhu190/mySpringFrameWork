package com.josie.annotation.enable;

import java.lang.annotation.*;

/**
 * @Author：Josie Zhu
 * @Description：启用切面拦截
 * @Date：Create in 12:51 2018/10/11
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface EnableAspcetProxy {
}
