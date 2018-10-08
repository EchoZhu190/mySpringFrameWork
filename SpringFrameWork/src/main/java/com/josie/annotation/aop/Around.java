package com.josie.annotation.aop;

import java.lang.annotation.*;

/**
 * Created by xiaoqin on 2018/10/5.
 */
@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Around {
    String value() default  "";
}
