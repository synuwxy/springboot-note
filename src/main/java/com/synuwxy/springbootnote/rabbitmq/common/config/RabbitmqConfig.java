package com.synuwxy.springbootnote.rabbitmq.common.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitmqConfig {

    public static final String QUEUE_NAME_QUEUE1 = "queue1";

    @Bean
    public Queue getQueue() {
        return new Queue(RabbitmqConfig.QUEUE_NAME_QUEUE1);
    }
}
