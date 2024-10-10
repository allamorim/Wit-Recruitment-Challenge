package com.AndreAmorim.Wit.Controllers;

import com.AndreAmorim.Wit.Models.CalculationFields;
import com.AndreAmorim.Wit.Services.CalculatorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculatorController {
    private static final Logger logger = LoggerFactory.getLogger(CalculatorService.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping(path = "/sum", produces = { MediaType.APPLICATION_JSON_VALUE })
    public String sum(@RequestParam("a") double a, @RequestParam("b") double b) {

        ObjectMapper objectMapper = new ObjectMapper();
        String result;

        try {
            String requestId = MDC.get("requestId");

            // include the request ID in headers of the message to the rabbitmq
            MessageProperties messageProperties = new MessageProperties();
            messageProperties.setHeader("requestId", requestId);

            // Writes the values as JSON
            String object = objectMapper.writeValueAsString(new CalculationFields(a, b));

            Message message = new Message(object.getBytes(), messageProperties);

            logger.info("A enviar a mensagem! (" + a + " + " + b + ")");

            // Send the message to RabbitMQ queue
            result = (String) rabbitTemplate.convertSendAndReceive("sumQueue", message);


        } catch (Exception e) {
            logger.info("Error while doing the calculation\n" +
                    "Details: " + e.getMessage());
            result = "Error while doing the calculation\n" +
                    "Details: " + e.getMessage();
        }

        return result;

    }

}
