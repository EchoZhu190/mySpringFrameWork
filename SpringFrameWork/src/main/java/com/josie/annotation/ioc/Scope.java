package com.josie.annotation.ioc;

import java.lang.annotation.*;

/**
 * @Author：Josie Zhu
 * @Description：
 * @Date：Create in 9:54 2018/10/10
 */
@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Scope {
    String value() default "single";
}
