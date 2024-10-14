package com.AndreAmorim.Wit.Services;

import com.AndreAmorim.Wit.Models.CalculationFields;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class CalculatorService implements ICalculatorService {

    private static final Logger logger = LoggerFactory.getLogger(CalculatorService.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = "sumQueue")
    @Override
    public BigDecimal Sum(Message message) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();

        // Deserialize the JSON to the object
        CalculationFields Fields = objectMapper.readerFor(CalculationFields.class).readValue(message.getBody());

        //Gets the header (RequestID)
        String requestId = (String) message.getMessageProperties().getHeaders().get("requestId");

        // Put the request ID into the MDC (in the listener rabbitmq)
        if (requestId != null) {
            MDC.put("requestId", requestId);
        }

        // Calculation
        BigDecimal result = (Fields.getFirstTerm().add(Fields.getSecondTerm()));

        logger.info("Received message, the result was: {}", result);

        return result;
    }

    @RabbitListener(queues = "subtractionQueue")
    @Override
    public BigDecimal Subtraction(Message message) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();

        // Deserialize the JSON to the object
        CalculationFields Fields = objectMapper.readerFor(CalculationFields.class).readValue(message.getBody());

        //Gets the header (RequestID)
        String requestId = (String) message.getMessageProperties().getHeaders().get("requestId");

        // Put the request ID into the MDC (in the listener rabbitmq)
        if (requestId != null) {
            MDC.put("requestId", requestId);
        }

        // Calculation
        BigDecimal result = (Fields.getFirstTerm().subtract(Fields.getSecondTerm()));

        logger.info("Received message, the result was: {}", result);

        return result;
    }

    @RabbitListener(queues = "multiplicationQueue")
    @Override
    public BigDecimal Multiplication(Message message) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();

        // Deserialize the JSON to the object
        CalculationFields Fields = objectMapper.readerFor(CalculationFields.class).readValue(message.getBody());

        //Gets the header (RequestID)
        String requestId = (String) message.getMessageProperties().getHeaders().get("requestId");

        // Put the request ID into the MDC (in the listener rabbitmq)
        if (requestId != null) {
            MDC.put("requestId", requestId);
        }

        // Calculation
        BigDecimal result = (Fields.getFirstTerm().multiply(Fields.getSecondTerm()));

        logger.info("Received message, the result was: {}", result);

        return result;
    }

    @RabbitListener(queues = "divisionQueue")
    @Override
    public BigDecimal Division(Message message) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();

        // Deserialize the JSON to the object
        CalculationFields Fields = objectMapper.readerFor(CalculationFields.class).readValue(message.getBody());

        //Gets the header (RequestID)
        String requestId = (String) message.getMessageProperties().getHeaders().get("requestId");

        // Put the request ID into the MDC (in the listener rabbitmq)
        if (requestId != null) {
            MDC.put("requestId", requestId);
        }

        // Calculation
        BigDecimal result = (Fields.getFirstTerm().divide(Fields.getSecondTerm(), 4, RoundingMode.HALF_EVEN));

        logger.info("Received message, the result was: {}", result);

        return result;
    }

}
