package com.josie.annotation;

import java.lang.annotation.*;

/**
 * @Author：Josie Zhu
 * @Description：
 * @Date：Create in 17:08 2018/9/29
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Configuration {
    String value() default "";
}
