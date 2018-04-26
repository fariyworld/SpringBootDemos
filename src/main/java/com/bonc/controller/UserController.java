package com.bonc.controller;

import com.bonc.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisSentinelConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Enumeration;
import java.util.Properties;
import java.util.Set;

/**
 * @Description: Redis
 *
 * Created by mace on 21:09 2018/4/10.
 */
@RestController
public class UserController {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @RequestMapping(value = "/getValue2Redis/{key}")
    public User getValue2Redis(@PathVariable String key){

        return (User)redisTemplate.opsForValue().get(key);
    }

    @RequestMapping(value = "/viewRedisPool")
    public String viewRedisPool(){

        RedisConnectionFactory redisConnectionFactory = redisTemplate.getConnectionFactory();

        JedisConnectionFactory jedisConnectionFactory = (JedisConnectionFactory) redisConnectionFactory;

        System.out.println("usePool: "+jedisConnectionFactory.getUsePool());

        return jedisConnectionFactory.getHostName();

    }

}
