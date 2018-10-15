package com.josie.annotation.aop;

import java.lang.annotation.*;

/**
 * Created by xiaoqin on 2018/10/5.
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Aspect {
    String pointCut() default  "";
}
