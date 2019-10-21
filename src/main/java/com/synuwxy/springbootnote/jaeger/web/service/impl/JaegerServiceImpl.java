package com.synuwxy.springbootnote.jaeger.web.service.impl;

import com.synuwxy.springbootnote.common.ResultCode;
import com.synuwxy.springbootnote.common.ResultObject;
import com.synuwxy.springbootnote.jaeger.web.async.RpcAsync;
import com.synuwxy.springbootnote.jaeger.web.service.JaegerAopService;
import com.synuwxy.springbootnote.jaeger.web.service.JaegerService;
import io.opentracing.Scope;
import io.opentracing.Span;
import io.opentracing.SpanContext;
import io.opentracing.Tracer;
import io.opentracing.propagation.Format;
import io.opentracing.propagation.TextMapExtractAdapter;
import io.opentracing.propagation.TextMapInjectAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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

    private final RpcAsync rpcAsync;

    @Autowired
    public JaegerServiceImpl(Tracer tracer, JaegerAopService jaegerAopService, RpcAsync rpcAsync) {
        this.tracer = tracer;
        this.jaegerAopService = jaegerAopService;
        this.rpcAsync = rpcAsync;
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
        try (Scope ignored = tracer.buildSpan("message").startActive(true)) {
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
        Map<String, Object> map = new HashMap<>(16);
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

    @Override
    public Map<String, Object> jaegerContextTest(String operationName) {
        try (Scope scope = getRootSpan(operationName)) {
            Span span = scope.span();
            span.log("调用时间是：" + System.currentTimeMillis());
            for (int i = 0; i < 10; i++) {
                String name = operationName + " 第" + i + "次操作";
                String url = "http://localhost:8080/jaeger/context/test?operationName=" + name + "&count=" + i;
                String uberTraceId = inject(span.context());
                rpcAsync.rpcRequest(url, uberTraceId);
            }
        }

        return ResultObject.newInstance("200", "jaegerContextTest");
    }

    @Override
    public String jaegerContextTransfer(String uberTraceId, String operationName, Integer count) {
        String resultMessage = "第 " + count + "次调用";
        try (Scope scope = startServerSpan(tracer, uberTraceId, operationName)) {
            Span span = scope.span();
            span.log(resultMessage);
        }
        return resultMessage;
    }

    private Scope getRootSpan(String operationName) {
        return tracer.buildSpan(operationName).startActive(true);
    }

    private String inject(SpanContext spanContext) {
        Map<String, String> map = new HashMap<>(16);
        tracer.inject(spanContext, Format.Builtin.HTTP_HEADERS, new TextMapInjectAdapter(map));
        return map.get("uber-trace-id");
    }

    private Scope startServerSpan(Tracer tracer, String uberTraceId, String operationName) {
        // format the headers for extraction
        HashMap<String, String> headers = new HashMap<>(16);
        headers.put("uber-trace-id",uberTraceId);
        Scope scope;
        try {
            SpanContext parentSpan = tracer.extract(Format.Builtin.HTTP_HEADERS, new TextMapExtractAdapter(headers));
            if (parentSpan == null) {
                scope = tracer.buildSpan(operationName).startActive(true);
            } else {
                scope = tracer.buildSpan(operationName).asChildOf(parentSpan).startActive(true);
            }
        } catch (IllegalArgumentException e) {
            scope = tracer.buildSpan(operationName).startActive(true);
        }
        return scope;
    }

    private String message(String msg, Long millis) {
        // Scope 支持try-catch-resource 方式回收
        try (Scope ignored = tracer.buildSpan("message").startActive(true)) {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
            ResultObject.newInstance(ResultCode.ERROR,"error");
        }
        return msg;
    }
}
