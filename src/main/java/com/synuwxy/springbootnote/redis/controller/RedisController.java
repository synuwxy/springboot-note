package com.synuwxy.springbootnote.redis.controller;

import com.synuwxy.springbootnote.common.ResultObject;
import com.synuwxy.springbootnote.redis.service.RedisService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author wxy
 * create by 2019.10.24
 */
@RestController
@RequestMapping("/redis")
public class RedisController {

    private final RedisService redisService;

    public RedisController(RedisService redisService) {
        this.redisService = redisService;
    }

    @GetMapping("/set")
    public Map<String, Object> setData(@RequestParam("key") String key, @RequestParam("value") String value) {
        redisService.setData(key, value);
        return ResultObject.newInstance("200", "success");
    }

    @GetMapping("/get")
    public Map<String, Object> getData(@RequestParam("key") String key) {
        Object object = redisService.getData(key);
        return ResultObject.newInstance("200", object);
    }
}
