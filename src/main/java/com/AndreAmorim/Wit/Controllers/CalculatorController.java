package com.AndreAmorim.Wit.Controllers;

import com.AndreAmorim.Wit.Models.CalculationFields;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculatorController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/sum")
    public String sum(@RequestParam("a") double a, @RequestParam("b") double b) {
        ObjectMapper objectMapper = new ObjectMapper();
        String result;
        try {
            // Write the message as JSON
            String object = objectMapper.writeValueAsString(new CalculationFields(a, b));
            // Send the message to RabbitMQ queue
            result = (String) rabbitTemplate.convertSendAndReceive("sumQueue", object);
            return result;
        } catch (Exception e) {
            return "Error while doing the calculation\n" +
                    "Details: " + e.getMessage();
        }
    }

}
