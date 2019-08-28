package com.synuwxy.springbootnote.rabbitmq.common.config;

import org.springframework.amqp.core.*;
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

    // 默认交换机队列
    public static final String DEFAULT_QUEUE = "defaultQueue";

    // Topic交换机模式队列
    public static final String TOPIC_QUEUE_1 = "topicQueue1";
    public static final String TOPIC_QUEUE_2 = "topicQueue2";

    // Fanout交换机队列
    public static final String FANOUT_QUEUE_1 = "fanoutQueue1";
    public static final String FANOUT_QUEUE_2 = "fanoutQueue2";
    public static final String FANOUT_QUEUE_3 = "fanoutQueue3";

    /**
     * 队列注册
     * @return Queue
     */
    @Bean
    public Queue getDefaultQueue() {
        return new Queue(RabbitmqConfig.DEFAULT_QUEUE);
    }

    @Bean
    public Queue getTopicQueue1() {
        return new Queue(RabbitmqConfig.TOPIC_QUEUE_1);
    }

    @Bean
    public Queue getTopicQueue2() {
        return new Queue(RabbitmqConfig.TOPIC_QUEUE_2);
    }

    @Bean
    public Queue getFanoutQueue1() {
        return new Queue(RabbitmqConfig.FANOUT_QUEUE_1);
    }

    @Bean
    public Queue getFanoutQueue2() {
        return new Queue(RabbitmqConfig.FANOUT_QUEUE_2);
    }

    @Bean
    public Queue getFanoutQueue3() {
        return new Queue(RabbitmqConfig.FANOUT_QUEUE_3);
    }

    public static final String TOPIC_ROUTINGKEY_1 = "topic.routingKey";
    public static final String TOPIC_ROUTINGKEY_ALL = "topic.#";

    // Topic交换机
    public static final String TOPICEXCHANGE = "topicExchange";

    // Fanout交换机
    public static final String FANOUTEXCHANGE = "fanoutExchange";

    /**
     * Topic交换机注册 Topic交换机可以使得通信双方使用 routingKey 进行消息传递
     * 即发送方指定routingKey发送消息,接收方接收指定routingKey的消息
     * @return TopicExchange
     */
    @Bean
    public TopicExchange getTopicExchange() {
        return new TopicExchange(TOPICEXCHANGE);
    }

    /**
     * Fanout交换机注册 广播方式
     * @return FanoutExchange
     */
    @Bean
    public FanoutExchange getFanoutExchange() {
        return new FanoutExchange(FANOUTEXCHANGE);
    }

    /**
     * 绑定 队列 TOPIC_QUEUE_1 到 TopicExchange交换机 TOPICEXCHANGE 设置路由key为 TOPIC_ROUTINGKEY_1
     * @param getTopicQueue1 队列
     * @param getTopicExchange TopicExchange交换机
     * @return Binding
     */
    @Bean
    public Binding bindingTopicExchange1(Queue getTopicQueue1, TopicExchange getTopicExchange){
        return BindingBuilder.bind(getTopicQueue1).to(getTopicExchange).with(TOPIC_ROUTINGKEY_1);
    }

    /**
     * 绑定 队列 TOPIC_QUEUE_2 到 TopicExchange交换机 TOPICEXCHANGE 设置路由key为 TOPIC_ROUTINGKEY_ALL
     * @param getTopicQueue2 队列
     * @param getTopicExchange TopicExchange交换机
     * @return Binding
     */
    @Bean
    public Binding bindingTopicExchangeAll(Queue getTopicQueue2, TopicExchange getTopicExchange){
        return BindingBuilder.bind(getTopicQueue2).to(getTopicExchange).with(TOPIC_ROUTINGKEY_ALL);
    }

    /**
     * 绑定 队列 FANOUT_QUEUE_1 到 FanoutExchange交换机 FANOUTEXCHANGE
     * @param getFanoutQueue1 队列
     * @param getFanoutExchange FanoutExchange交换机
     * @return Binding
     */
    @Bean
    public Binding bindingFanoutExchange1(Queue getFanoutQueue1, FanoutExchange getFanoutExchange){
        return BindingBuilder.bind(getFanoutQueue1).to(getFanoutExchange);
    }

    /**
     * 绑定 队列 FANOUT_QUEUE_2 到 FanoutExchange交换机 FANOUTEXCHANGE
     * @param getFanoutQueue2 队列
     * @param getFanoutExchange FanoutExchange交换机
     * @return Binding
     */
    @Bean
    public Binding bindingFanoutExchange2(Queue getFanoutQueue2, FanoutExchange getFanoutExchange){
        return BindingBuilder.bind(getFanoutQueue2).to(getFanoutExchange);
    }

    /**
     * 绑定 队列 FANOUT_QUEUE_3 到 FanoutExchange交换机 FANOUTEXCHANGE
     * @param getFanoutQueue3 队列
     * @param getFanoutExchange FanoutExchange交换机
     * @return Binding
     */
    @Bean
    public Binding bindingFanoutExchange3(Queue getFanoutQueue3, FanoutExchange getFanoutExchange){
        return BindingBuilder.bind(getFanoutQueue3).to(getFanoutExchange);
    }
}