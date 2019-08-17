package com.synuwxy.springbootnote.jaeger.web.service.impl;

import com.synuwxy.springbootnote.jaeger.web.service.JaegerAopService;
import org.springframework.stereotype.Service;

/**
 * @author wxy
 * create by 2019.08.17
 *
 * desc:
 * jaeger aop 方式的测试接口
 */
@Service
public class JaegerAopServiceImpl implements JaegerAopService {
    @Override
    public void jaegerSendTest1() throws InterruptedException {
        Thread.sleep(500);
    }

    @Override
    public void jaegerSendTest2() throws InterruptedException {
        Thread.sleep(1000);
    }
}
