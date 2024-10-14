package com.AndreAmorim.Wit.Utils;

import com.AndreAmorim.Wit.Models.CalculationFields;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;

@Component
public class Utils implements IUtils {

    @Override
    public Message CreateMessage(BigDecimal a, BigDecimal b, String requestId) throws JsonProcessingException {

        // include the request ID in headers of the message to the rabbitmq
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setHeader("requestId", requestId);

        ObjectMapper objectMapper = new ObjectMapper();
        // Writes the values as JSON
        String object = objectMapper.writeValueAsString(new CalculationFields(a, b));

        return new Message(object.getBytes(), messageProperties);
    }
}
