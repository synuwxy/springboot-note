package com.synuwxy.sample.jaeger.web.controller;

import com.synuwxy.sample.jaeger.annotation.JaegerAutoSend;
import com.synuwxy.sample.jaeger.web.service.JaegerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author wxy
 * Create by 2019.08.17
 * <p>
 * desc:
 * jaeger试验controller
 */
@RestController
@RequestMapping("/jaeger")
public class JaegerController {

    private final JaegerService jaegerService;

    @Autowired
    public JaegerController(JaegerService jaegerService) {
        this.jaegerService = jaegerService;
    }

    /**
     * 向服务端发送接口信息(基本的方法)
     *
     * @return json
     */
    @GetMapping("/build/span")
    public Map<String, Object> jaegerBuildSpanTest() {
        return jaegerService.jaegerBuildSpanTest();
    }

    /**
     * 向服务端发送接口信息(scope方式)
     *
     * @return json
     */
    @GetMapping("/scope")
    public Map<String, Object> jaegerScopeTest() {
        return jaegerService.jaegerScopeTest();
    }

    /**
     * 向服务端发送接口信息,并附带日志
     *
     * @return json
     */
    @GetMapping("/log")
    public Map<String, Object> jaegerLogTest() {
        return jaegerService.jaegerLogTest();
    }

    /**
     * 使用aop的方式发送信息
     *
     * @return json
     */
    @GetMapping("/aop")
    public Map<String, Object> jaegerAopTest() {
        return jaegerService.jaegerAopTest();
    }

    /**
     * 使用注解的方式发送信息
     *
     * @return json
     */
    @JaegerAutoSend
    @GetMapping("/annotation")
    public Map<String, Object> jaegerAnnotationTest() {
        return jaegerService.jaegerAnnotationTest();
    }

    /**
     * span context
     *
     * @param operationName 操作名称
     * @return json
     */
    @GetMapping("/context")
    public Map<String, Object> jaegerContextTest(@RequestParam("operationName") String operationName) {
        return jaegerService.jaegerContextTest(operationName);
    }

    @GetMapping("/context/test")
    public String jaegerContextTest(@RequestHeader("uberTraceId") String uberTraceId, @RequestParam("operationName") String operationName, Integer count) {
        return jaegerService.jaegerContextTransfer(uberTraceId, operationName, count);
    }
}
