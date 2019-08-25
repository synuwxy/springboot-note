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

@RestController
@RequestMapping("/rabbitmq")
public class RabbitSenderController {

    private final AmqpTemplate rabbitmqTemplate;

    public RabbitSenderController(AmqpTemplate rabbitmqTemplate) {
        this.rabbitmqTemplate = rabbitmqTemplate;
    }

    @GetMapping("/send")
    public Map<String, Object> sendMessage(@RequestParam("msg") String msg) {
        rabbitmqTemplate.convertAndSend(RabbitmqConfig.QUEUE_NAME_QUEUE1,msg);
        return ResultObject.newInstance(ResultCode.SUCCESS,msg);
    }
}
