package com.synuwxy.springbootnote.jaeger.annotation;

import java.lang.annotation.*;

/**
 * @author wxy
 * create by 2019.08.17
 *
 * desc:
 * 自定义的jaeger发送注解,实现注解方式发送jaeger的检测信息
 */
@Target({ElementType.TYPE,ElementType.METHOD,ElementType.PACKAGE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface JaegerAutoSend {
    String tag() default "";
}