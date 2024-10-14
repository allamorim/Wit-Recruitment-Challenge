package com.AndreAmorim.Wit.Services;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import java.io.IOException;
import java.math.BigDecimal;

public interface ICalculatorService {
    /// Calculation of a sum between two numbers
    /// @param message Message with two numbers serialized in JSON and the RequestID
    /// @return Result between a+b
    @RabbitListener(queues = "sumQueue")
    BigDecimal Sum(Message message) throws IOException;

    /// Calculation of a subtraction between two numbers
    /// @param message Message with two numbers serialized in JSON and the RequestID
    /// @return Result between a-b
    @RabbitListener(queues = "subtractionQueue")
    BigDecimal Subtraction(Message message) throws IOException;

    /// Calculation of a multiplication between two numbers
    /// @param message Message with two numbers serialized in JSON and the RequestID
    /// @return Result between a*b
    @RabbitListener(queues = "multiplicationQueue")
    BigDecimal Multiplication(Message message) throws IOException;

    /// Calculation of a division between two numbers
    /// @param message Message with two numbers serialized in JSON and the RequestID
    /// @return Result between a/b
    @RabbitListener(queues = "divisionQueue")
    BigDecimal Division(Message message) throws IOException;
}
