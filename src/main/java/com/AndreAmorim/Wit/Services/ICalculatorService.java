package com.AndreAmorim.Wit.Services;

import com.AndreAmorim.Wit.Models.CalculationFields;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import java.io.IOException;
import java.math.BigDecimal;

public interface ICalculatorService {
    /// Calculation of a sum between two numbers
    /// @param message Message with two numbers serialized in JSON and the RequestID
    /// @return Result between a+b
    @RabbitListener(queues = "sumQueue", returnExceptions = "true")
    BigDecimal Sum(Message message) throws IOException;

    /// Calculation of a subtraction between two numbers
    /// @param message Message with two numbers serialized in JSON and the RequestID
    /// @return Result between a-b
    @RabbitListener(queues = "subtractionQueue", returnExceptions = "true")
    BigDecimal Subtraction(Message message) throws IOException;

    /// Calculation of a multiplication between two numbers
    /// @param message Message with two numbers serialized in JSON and the RequestID
    /// @return Result between a*b
    @RabbitListener(queues = "multiplicationQueue", returnExceptions = "true")
    BigDecimal Multiplication(Message message) throws IOException;

    /// Calculation of a division between two numbers
    /// @param message Message with two numbers serialized in JSON and the RequestID
    /// @return Result between a/b
    @RabbitListener(queues = "divisionQueue", returnExceptions = "true")
    Object Division(Message message) throws Exception;

    /// Extraction of the fields in the message
    /// @param message Message with two numbers serialized in JSON
    /// @return the values for the calculation
    CalculationFields extractFields(Message message) throws IOException;
}
