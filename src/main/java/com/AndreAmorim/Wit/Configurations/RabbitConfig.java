package com.AndreAmorim.Wit.Configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Bean
    public Queue SumQueue() {
        return new Queue("sumQueue");
    }

    @Bean
    public Queue SubtractionQueue() {
        return new Queue("subtractionQueue");
    }

    @Bean
    public Queue DivisionQueue() {
        return new Queue("divisionQueue");
    }

    @Bean
    public Queue MultiplicationQueue() {
        return new Queue("multiplicationQueue");
    }

}
