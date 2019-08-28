package com.synuwxy.springbootnote.rabbitmq.web.listener;

import com.synuwxy.springbootnote.rabbitmq.common.config.RabbitmqConfig;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author wxy
 * create by 2019.08.25
 *
 * desc:
 * rabbitmq监听
 */
@RabbitListener(queues = RabbitmqConfig.DEFAULT_QUEUE)
@Component
public class RabbitmqReceiver {

    @RabbitHandler
    public void receive(String msg) {
        System.out.println("来自于队列 " + RabbitmqConfig.DEFAULT_QUEUE + " 的消息是: " + msg);
    }
}
