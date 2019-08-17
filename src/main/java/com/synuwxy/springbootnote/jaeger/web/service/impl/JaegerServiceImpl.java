package com.synuwxy.springbootnote.jaeger.web.service.impl;

import com.synuwxy.springbootnote.common.ResultCode;
import com.synuwxy.springbootnote.common.ResultObject;
import com.synuwxy.springbootnote.jaeger.web.service.JaegerAopService;
import com.synuwxy.springbootnote.jaeger.web.service.JaegerService;
import io.opentracing.Scope;
import io.opentracing.Span;
import io.opentracing.Tracer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wxy
 * create by 2019.08.17
 *
 * desc:
 * jaeger 功能实现类
 */
@Service
public class JaegerServiceImpl implements JaegerService {

    private final Tracer tracer;

    private final JaegerAopService jaegerAopService;

    @Autowired
    public JaegerServiceImpl(Tracer tracer, JaegerAopService jaegerAopService) {
        this.tracer = tracer;
        this.jaegerAopService = jaegerAopService;
    }

    @Override
    public Map<String, Object> jaegerBuildSpanTest() {
        // 创建一个Span
        Span span = tracer.buildSpan("buildSpanTest").start();
        span.setTag("tagTest","str");
        try {
            // 延时 1s ，方便查看
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            ResultObject.newInstance(ResultCode.ERROR,"error");
        }finally {
            // Span 必须回收，不然数据发送不出去
            span.finish();
        }
        return ResultObject.newInstance(ResultCode.SUCCESS,"send span success");
    }

    @Override
    public Map<String, Object> jaegerScopeTest() {
        String msg = "";
        // 创建一个Scope，scope会逐层回收，所有请求结果都会统计到最外层的方法上
        try (Scope scope = tracer.buildSpan("message").startActive(true)) {
            msg += message("hello ",500L);
            msg += message("jaeger",1000L);
        }
        return ResultObject.newInstance(ResultCode.SUCCESS,msg);
    }

    @Override
    public Map<String, Object> jaegerLogTest() {
        StringBuilder msg = new StringBuilder();
        Span span = tracer.buildSpan("buildSpanTest").start();
        try {
            for (int i = 1;i <= 5;i++) {
                Thread.sleep(i * 100);
                msg.append(i);
                span.log("append --- " + i);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            ResultObject.newInstance(ResultCode.ERROR,"error");
        } finally {
            span.finish();
        }
        return ResultObject.newInstance(ResultCode.SUCCESS,msg);
    }

    @Override
    public Map<String, Object> jaegerAopTest() {
        Map<String, Object> map = new HashMap<>();
        try {
            jaegerAopService.jaegerSendTest1();
            jaegerAopService.jaegerSendTest2();
            map.put(ResultCode.SUCCESS,"success");
        } catch (InterruptedException e) {
            e.printStackTrace();
            map.put(ResultCode.ERROR,"error");
        }
        return map;
    }

    /**
     * 注解方式 实现的方式与aop一致,直接使用即可
     * @return json
     */
    @Override
    public Map<String, Object> jaegerAnnotationTest() {
        return jaegerAopTest();
    }

    private String message(String msg, Long millis) {
        // Scope 支持try-catch-resource 方式回收
        try (Scope scope = tracer.buildSpan("message").startActive(true)) {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
            ResultObject.newInstance(ResultCode.ERROR,"error");
        }
        return msg;
    }
}
