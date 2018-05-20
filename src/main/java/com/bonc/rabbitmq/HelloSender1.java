package com.bonc.rabbitmq;

import com.bonc.domain.User;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * description:
 * <br />
 * Created by mace on 21:48 2018/5/19.
 */
@Component
public class HelloSender1 {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send(int count) {
        String context = "hello " + System.currentTimeMillis() + " " + count;
        System.out.println("Sender1 : " + context);
        this.rabbitTemplate.convertAndSend("spring-boot-queue", context);
    }

    public void send(User user) {

        System.out.println("Sender1 object: " + user.toString());
        this.rabbitTemplate.convertAndSend("spring-boot-queue", user);
    }
}
