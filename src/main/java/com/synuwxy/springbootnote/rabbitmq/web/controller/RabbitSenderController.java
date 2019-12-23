package com.synuwxy.springbootnote.rabbitmq.web.controller;

import com.synuwxy.springbootnote.common.ResultCode;
import com.synuwxy.springbootnote.common.ResultObject;
import com.synuwxy.springbootnote.rabbitmq.common.config.RabbitmqConfig;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author wxy
 * create by 2019.08.25
 * <p>
 * desc:
 * rabbitmq发送端
 */
@RestController
@RequestMapping("/rabbitmq")
public class RabbitSenderController {

    private final AmqpTemplate rabbitmqTemplate;

    @Autowired
    public RabbitSenderController(AmqpTemplate rabbitmqTemplate) {
        this.rabbitmqTemplate = rabbitmqTemplate;
    }

    @GetMapping("/default")
    public Map<String, Object> defaultSendMessage(@RequestParam("msg") String msg) {
        rabbitmqTemplate.convertAndSend(RabbitmqConfig.DEFAULT_QUEUE, msg);
        return ResultObject.newInstance(ResultCode.SUCCESS, msg);
    }

    @GetMapping("/topic")
    public Map<String, Object> topicSendMessage(@RequestParam("msg") String msg, String routingKey) {
        rabbitmqTemplate.convertAndSend(RabbitmqConfig.TOPICEXCHANGE, routingKey, msg);
        return ResultObject.newInstance(ResultCode.SUCCESS, msg);
    }

    @GetMapping("/fanout")
    public Map<String, Object> fanoutSendMessage(@RequestParam("msg") String msg) {
        String routingKey = "";
        rabbitmqTemplate.convertAndSend(RabbitmqConfig.FANOUTEXCHANGE, routingKey, msg);
        return ResultObject.newInstance(ResultCode.SUCCESS, msg);
    }
}
