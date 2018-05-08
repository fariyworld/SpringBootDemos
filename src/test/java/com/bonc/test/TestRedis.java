package com.bonc.test;

import com.bonc.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @Description: test redis with springboot
 *
 * Created by mace on 15:02 2018/4/10.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestRedis {

    @Resource
    private RedisTemplate<String,Object> redisTemplate;


    @Test
    public void testRedis(){

        System.out.println(redisTemplate.opsForValue().get("testKey1"));

        User user = new User();
        user.setAge(18);
        user.setName("尚宇烨");

        redisTemplate.opsForValue().set("user1",user);
    }

}
