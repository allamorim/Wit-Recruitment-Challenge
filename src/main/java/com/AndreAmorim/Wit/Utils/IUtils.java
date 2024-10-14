package com.AndreAmorim.Wit.Utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.amqp.core.Message;

import java.math.BigDecimal;

public interface IUtils {

    /// Creation of the message to send to RabbitMq for the operation
    /// @param a First Value
    /// @param b Second Value
    /// @param requestId Id do Rest Request
    /// @return Message
    Message CreateMessage(BigDecimal a, BigDecimal b, String requestId) throws JsonProcessingException;
}
