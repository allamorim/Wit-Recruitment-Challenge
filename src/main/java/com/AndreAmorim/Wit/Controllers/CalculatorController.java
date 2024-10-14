package com.AndreAmorim.Wit.Controllers;

import com.AndreAmorim.Wit.Models.ApiResponse;
import com.AndreAmorim.Wit.Utils.IUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CalculatorController {
    private static final Logger logger = LoggerFactory.getLogger(CalculatorController.class);

    private final IUtils utils;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    public CalculatorController(IUtils utils) {
        this.utils = utils;
    }

    /// Sum between two numbers
    /// @param a First Value
    /// @param b Second Value
    /// @return ApiResponse
    @GetMapping(path = "/sum", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ApiResponse<BigDecimal> Sum(@RequestParam("a") BigDecimal a, @RequestParam("b") BigDecimal b) throws JsonProcessingException {

        ApiResponse<BigDecimal> rs = new ApiResponse<BigDecimal>();

        // Creates the message to send to rabbitmq
        Message message = utils.CreateMessage(a, b, MDC.get("requestId"));

        logger.info("Sending message! ({} + {})", a, b);

        // Send the message to RabbitMQ queue
        rs.setData((BigDecimal) rabbitTemplate.convertSendAndReceive("sumQueue", message));

        return rs;
    }

    /// Subtraction between two numbers
    /// @param a First Value
    /// @param b Second Value
    /// @return ApiResponse
    @GetMapping(path = "/subtraction", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ApiResponse<BigDecimal> Subtraction(@RequestParam("a") BigDecimal a, @RequestParam("b") BigDecimal b) throws JsonProcessingException {

        ApiResponse<BigDecimal> rs = new ApiResponse<>();

        // Creates the message to send to rabbitmq
        Message message = utils.CreateMessage(a, b, MDC.get("requestId"));

        logger.info("Sending message! ({} - {})", a, b);

        // Send the message to RabbitMQ queue
        rs.setData((BigDecimal) rabbitTemplate.convertSendAndReceive("subtractionQueue", message));

        return rs;
    }

    /// Multiplication between two numbers
    /// @param a First Value
    /// @param b Second Value
    /// @return ApiResponse
    @GetMapping(path = "/multiplication", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ApiResponse<BigDecimal> Multiplication(@RequestParam("a") BigDecimal a, @RequestParam("b") BigDecimal b) throws JsonProcessingException {

        ApiResponse<BigDecimal> rs = new ApiResponse<>();

        // Creates the message to send to rabbitmq
        Message message = utils.CreateMessage(a, b, MDC.get("requestId"));

        logger.info("Sending message! ({} * {})", a, b);

        // Send the message to RabbitMQ queue
        rs.setData((BigDecimal) rabbitTemplate.convertSendAndReceive("multiplicationQueue", message));

        return rs;
    }

    /// Division between two numbers
    /// @param a First Value
    /// @param b Second Value
    /// @return ApiResponse
    @GetMapping(path = "/division", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ApiResponse<BigDecimal> Division(@RequestParam("a") BigDecimal a, @RequestParam("b") BigDecimal b) throws JsonProcessingException {

        ApiResponse<BigDecimal> rs = new ApiResponse<>();

        // Creates the message to send to rabbitmq
        Message message = utils.CreateMessage(a, b, MDC.get("requestId"));

        logger.info("Sending message! ({} / {})", a, b);

        // Send the message to RabbitMQ queue
        rs.setData((BigDecimal) rabbitTemplate.convertSendAndReceive("divisionQueue", message));

        return rs;
    }
}
