package com.josie.annotation;

import java.lang.annotation.*;

/**
 * @Author：Josie Zhu
 * @Description：
 * @Date：Create in 10:30 2018/8/20
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ComponentScan {
    /**
     * 单个包及子包
     */
    String basePackage() default "";
    /**
     * @return
     *
     */
    String[] value() default {};

    /**
     * 扫描包
     *
     * @return
     */
    String[] basePackages() default {};

    /**
     * 扫描的基类
     *
     * @return
     */
    Class<?>[] basePackageClasses() default {};

}
