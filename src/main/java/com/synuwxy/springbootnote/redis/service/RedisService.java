package com.synuwxy.springbootnote.redis.service;


/**
  * @author wxy
  * create by 2019.10.24
 */
public interface RedisService {

    void setData(String key, String value);

    Object getData(String key);
}
