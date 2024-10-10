package com.AndreAmorim.Wit.Services;

import com.AndreAmorim.Wit.Models.CalculationFields;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CalculatorService {

    private static final Logger logger = LoggerFactory.getLogger(CalculatorService.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = "sumQueue")
    public String Sum(Message message) {

        ObjectMapper objectMapper = new ObjectMapper();
        try{

            // Deserialize the JSON to the object
            CalculationFields Fields = objectMapper.readerFor(CalculationFields.class).readValue(message.getBody());

            //Gets the header (RequestID)
            String requestId = (String) message.getMessageProperties().getHeaders().get("requestId");

            // Put the request ID into the MDC (in the listener rabbitmq)
            if (requestId != null) {
                MDC.put("requestId", requestId);
            }

            // Calculation
            double result = (Fields.getFirstTerm() + Fields.getSecondTerm());

            logger.info("Received message, the result was: {}", result);

            return "Result: " + result;
        }
        catch (Exception e) {
            return "Error while doing the calculation\n" +
                    "Details: " + e.getMessage();
        }


    }
}
