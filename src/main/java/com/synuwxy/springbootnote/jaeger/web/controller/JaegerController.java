package com.synuwxy.springbootnote.jaeger.web.controller;

import com.synuwxy.springbootnote.jaeger.annotation.JaegerAutoSend;
import com.synuwxy.springbootnote.jaeger.web.service.JaegerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author wxy
 * Create by 2019.08.17
 *
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
     * @return json
     */
    @GetMapping("/build/span")
    public Map<String, Object> jaegerBuildSpanTest() {
        return jaegerService.jaegerBuildSpanTest();
    }

    /**
     * 向服务端发送接口信息(scope方式)
     * @return json
     */
    @GetMapping("/scope")
    public Map<String, Object> jaegerScopeTest() {
        return jaegerService.jaegerScopeTest();
    }

    /**
     * 向服务端发送接口信息,并附带日志
     * @return json
     */
    @GetMapping("/log")
    public Map<String, Object> jaegerLogTest() {
        return jaegerService.jaegerLogTest();
    }

    /**
     * 使用aop的方式发送信息
     * @return json
     */
    @GetMapping("/aop")
    public Map<String, Object> jaegerAopTest() {
        return jaegerService.jaegerAopTest();
    }

    /**
     * 使用注解的方式发送信息
     * @return json
     */
    @JaegerAutoSend
    @GetMapping("/annotation")
    public Map<String, Object> jaegerAnnotationTest() {
        return jaegerService.jaegerAnnotationTest();
    }
}
