package com.AndreAmorim.Wit.Configurations;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.RemoteInvocationAwareMessageConverterAdapter;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import java.util.List;

@Configuration
public class RabbitConfig {

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setReplyTimeout(6000);
        rabbitTemplate.setMessageConverter(remoteInvocationAwareMessageConverterAdapter());
        return rabbitTemplate;
    }

    @Bean("remoteInvocationAwareMessageConverter")
    @Primary
    public RemoteInvocationAwareMessageConverterAdapter remoteInvocationAwareMessageConverterAdapter() {
        SimpleMessageConverter converter = new SimpleMessageConverter();
        converter.setAllowedListPatterns(List.of("org.springframework.amqp.support.converter.RemoteInvocationResult", "java.lang.*", "java.util.*"));
        return new RemoteInvocationAwareMessageConverterAdapter(converter);
    }

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
