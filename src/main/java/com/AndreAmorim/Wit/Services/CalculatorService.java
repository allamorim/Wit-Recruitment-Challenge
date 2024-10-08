package com.AndreAmorim.Wit.Services;

import com.AndreAmorim.Wit.Models.CalculationFields;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CalculatorService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = "sumQueue")
    public String Sum(String terms) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        // Deserialize the JSON to the object
        CalculationFields Fields = objectMapper.readValue(terms, CalculationFields.class);
        // Calculation
        String result = "Result: " + (Fields.getFirstTerm() + Fields.getSecondTerm());
        return result;

    }
}
