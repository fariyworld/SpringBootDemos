package com.bonc.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * description:
 * <br />
 * Created by mace on 17:25 2018/5/18.
 */
@Configuration
@EnableRedisHttpSession(redisNamespace = "springboot-session-redis")//开启spring session支持
public class RedisSessionConfig {

}
