package com.synuwxy.springbootnote.rabbitmq.web.listener;

import com.synuwxy.springbootnote.rabbitmq.common.config.RabbitmqConfig;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@RabbitListener(queues = RabbitmqConfig.QUEUE_NAME_QUEUE1)
@Component
public class RabbitmqReceiver {

    @RabbitHandler
    public void receive(String msg) {
        System.out.println("来自于队列 " + RabbitmqConfig.QUEUE_NAME_QUEUE1 + " 的消息是: " + msg);
    }
}
