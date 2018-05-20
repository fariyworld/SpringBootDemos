package com.bonc.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * description:
 * <br />
 * Created by mace on 21:48 2018/5/19.
 */
@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue queue(){
        //true 队列持久
        return new Queue("spring-boot-queue", false);
    }
}
