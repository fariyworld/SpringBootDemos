package com.bonc.controller;

import com.bonc.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: Redis
 *
 * Created by mace on 21:09 2018/4/10.
 */
@RestController
public class UserController {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @RequestMapping("/getValue2Redis/{key}")
    public User getValue2Redis(@PathVariable String key){

        return (User)redisTemplate.opsForValue().get(key);
    }

}
