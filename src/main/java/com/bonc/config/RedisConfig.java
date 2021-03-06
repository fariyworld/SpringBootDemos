package com.bonc.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @Description: Created by mace on 15:18 2018/4/10.
 */

@Configuration
@EnableCaching//启用缓存,这个注解很重要
public class RedisConfig extends CachingConfigurerSupport {

    private static final Logger logger = LoggerFactory.getLogger(RedisConfig.class);

    /**
     * 缓存管理器.
     * @param redisTemplate
     * @return
     */
    @Bean
    public CacheManager cacheManager(RedisTemplate<?, ?> redisTemplate) {

        CacheManager cacheManager = new RedisCacheManager(redisTemplate);

        return cacheManager;
    }

    /**
     * redis模板操作类,类似于jdbcTemplate的一个类;
     * <br />
     * 解决redis自动生成key，key乱码，为自定义key
     * <br />
     * @param factory 通过Spring进行注入，参数在application.yml进行配置；
     * @return
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {

        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
        redisTemplate.setConnectionFactory(factory);

//        RedisConnectionFactory redisConnectionFactory = factory;
//        JedisConnectionFactory jedisConnectionFactory = (JedisConnectionFactory) redisConnectionFactory;
//        logger.info("hostname: "+jedisConnectionFactory.getHostName());
//        logger.info("use pool: "+jedisConnectionFactory.getUsePool());
//        logger.info("password: "+jedisConnectionFactory.getPassword());
//        logger.info("port: "+jedisConnectionFactory.getPort());
//        logger.info("datebase: "+jedisConnectionFactory.getDatabase());
//        logger.info("timeout: "+jedisConnectionFactory.getTimeout());
//        JedisPoolConfig poolConfig = jedisConnectionFactory.getPoolConfig();
//        logger.info("max-idle: "+poolConfig.getMaxIdle());
//        logger.info("max-wait: "+poolConfig.getMaxWaitMillis());
//        logger.info("min-idle: "+poolConfig.getMinIdle());

        RedisSerializer<String> redisSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(redisSerializer);
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);

        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.afterPropertiesSet();

        return redisTemplate;
    }

}
