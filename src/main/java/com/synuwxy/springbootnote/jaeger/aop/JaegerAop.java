package com.synuwxy.springbootnote.jaeger.aop;

import io.opentracing.Scope;
import io.opentracing.Tracer;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author wxy
 * Create by 2019.08.17
 *
 * desc:
 * jaeger aop 配置
 */
@Component
@Aspect
public class JaegerAop {

    private final Tracer tracer;

    /**
     * jaegerAopStatus 用于控制jaeger的全局AOP是否生效,在配置文件中配置,默认应为false
     */
    @Value("${opentracing.jaeger.aop.status}")
    private Boolean jaegerAopStatus;

    @Autowired
    public JaegerAop(Tracer tracer) {
        this.tracer = tracer;
    }

    /**
     * 环绕通知 默认扫描web包下的所有方法，默认是关闭状态
     * 优先级设为 5 jaeger的全局aop的优先级应当尽量靠后
     * @param proceedingJoinPoint 切点
     * @return obj
     * @throws Throwable Exception
     */
    @Order(5)
    @Around("execution(* com.synuwxy.springbootnote.jaeger.web..*.* (..))")
    public Object arroundAll(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        //若设置全局AOP不生效,直接放行
        if (!jaegerAopStatus) {
            return proceedingJoinPoint.proceed();
        }
        //若设置全局AOP生效,扫描所有的方法
        String methodName = proceedingJoinPoint.getSignature().getName();
        try (Scope scope = tracer.buildSpan(methodName).startActive(true)) {
            return proceedingJoinPoint.proceed();
        }
    }

    /**
     * 环绕通知 自定义注解扫描，用于使用注解发送jaeger数据
     * @param proceedingJoinPoint 切点
     * @return obj
     * @throws Throwable Exception
     */
    @Order(1)
    @Around(value = "@annotation(com.synuwxy.springbootnote.jaeger.annotation.JaegerAutoSend)")
    public Object arround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        String methodName = proceedingJoinPoint.getSignature().getName();
        try (Scope scope = tracer.buildSpan(methodName).startActive(true)){
            return proceedingJoinPoint.proceed();
        }
    }
}
