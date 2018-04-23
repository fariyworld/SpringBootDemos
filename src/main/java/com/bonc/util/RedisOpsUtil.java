package com.bonc.util;

import org.springframework.data.redis.core.RedisTemplate;

/**
 * @Description:
 *
 * Created by mace on 8:43 2018/4/11.
 */
public class RedisOpsUtil<String,V> {

    private RedisTemplate<String,V> redisTemplate;

    public RedisOpsUtil() { }

    public RedisOpsUtil(RedisTemplate<String, V> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }


}
