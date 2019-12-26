package com.synuwxy.sample.jaeger.web.service;

import org.springframework.http.HttpHeaders;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface JaegerService {

    /**
     * jaeger build span 方式
     *
     * @return json
     */
    Map<String, Object> jaegerBuildSpanTest();

    /**
     * jaeger Scope 方式
     *
     * @return json
     */
    Map<String, Object> jaegerScopeTest();

    /**
     * jaeger log 方式
     *
     * @return json
     */
    Map<String, Object> jaegerLogTest();

    /**
     * jaeger aop 方式
     * 为了简化jaeger对代码的侵入,使用aop方式进行配置可以做到不动原有代码,也能获得jaeger的效果
     * 所以这里模拟实现一个接口做测试
     *
     * @return json
     */
    Map<String, Object> jaegerAopTest();

    /**
     * jaeger annotation 方式
     * 和aop思路一致,避免对代码的侵入,但又不想完全扫描的解决方案
     * 所以与aop一样,模拟一个接口
     *
     * @return json
     */
    Map<String, Object> jaegerAnnotationTest();

    /**
     * @param operationName 操作名称
     * @return map
     */
    Map<String, Object> jaegerContextTest(String operationName);

    /**
     * @param uberTraceId   tranceId
     * @param operationName 操作名称
     * @param count         线程数
     * @return 操作
     */
    String jaegerContextTransfer(String uberTraceId, String operationName, Integer count);
}
