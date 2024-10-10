package com.AndreAmorim.Wit.Configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    // Queue where messages will be sent (Sum messages)
    @Bean
    public Queue SumQueue() {
        return new Queue("sumQueue");
    }


}
