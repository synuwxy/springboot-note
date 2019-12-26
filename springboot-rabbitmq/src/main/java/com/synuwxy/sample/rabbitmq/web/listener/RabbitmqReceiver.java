package com.synuwxy.sample.rabbitmq.web.listener;

import com.synuwxy.sample.rabbitmq.common.config.RabbitmqConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author wxy
 * create by 2019.08.25
 * <p>
 * desc:
 * rabbitmq监听
 */

@Component
public class RabbitmqReceiver {

    @RabbitListener(queues = RabbitmqConfig.DEFAULT_QUEUE)
    public void defaultReceive(String msg) {
        System.out.println("来自于队列 " + RabbitmqConfig.DEFAULT_QUEUE + " 的消息是: " + msg);
    }

    @RabbitListener(queues = RabbitmqConfig.TOPIC_QUEUE_1)
    public void topicReceive1(String msg) {
        System.out.println("来自于队列 " + RabbitmqConfig.TOPIC_QUEUE_1 + " 的消息是: " + msg);
    }

    @RabbitListener(queues = RabbitmqConfig.TOPIC_QUEUE_2)
    public void topicReceive2(String msg) {
        System.out.println("来自于队列 " + RabbitmqConfig.TOPIC_QUEUE_2 + " 的消息是: " + msg);
    }

    @RabbitListener(queues = RabbitmqConfig.FANOUT_QUEUE_1)
    public void fanoutReceive1(String msg) {
        System.out.println("来自于队列 " + RabbitmqConfig.FANOUT_QUEUE_1 + " 的消息是: " + msg);
    }

    @RabbitListener(queues = RabbitmqConfig.FANOUT_QUEUE_2)
    public void fanoutReceive2(String msg) {
        System.out.println("来自于队列 " + RabbitmqConfig.FANOUT_QUEUE_2 + " 的消息是: " + msg);
    }

    @RabbitListener(queues = RabbitmqConfig.FANOUT_QUEUE_3)
    public void fanoutReceive3(String msg) {
        System.out.println("来自于队列 " + RabbitmqConfig.FANOUT_QUEUE_3 + " 的消息是: " + msg);
    }
}