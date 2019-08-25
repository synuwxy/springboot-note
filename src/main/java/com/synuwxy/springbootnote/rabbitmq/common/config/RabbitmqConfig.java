package com.synuwxy.springbootnote.rabbitmq.common.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wxy
 * create by 2019.08.25
 *
 * desc:
 * rabbitmq 配置类,主要用于配置全局参数,定义队列或交换机等
 */
@Configuration
public class RabbitmqConfig {

    public static final String QUEUE_NAME_QUEUE1 = "queue1";

    @Bean
    public Queue getQueue() {
        return new Queue(RabbitmqConfig.QUEUE_NAME_QUEUE1);
    }
}
