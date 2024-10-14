package com.AndreAmorim.Wit.Handlers;

import com.AndreAmorim.Wit.Models.ApiResponse;
import com.AndreAmorim.Wit.Models.ApiResponseDetails;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler()
    @ResponseBody
    public ResponseEntity<Object> handleRuntimeException(RuntimeException exception){

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        ApiResponse<Object> response = new ApiResponse<>();

        response.setSuccessfull(false);

        response.setErrorDetails(new ApiResponseDetails(exception.getMessage()));

        ObjectMapper objectMapper = new ObjectMapper();

        try
        {
            String ResponseError = objectMapper.writeValueAsString(response);
            logger.info(ResponseError);
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .headers(headers)
                    .body(ResponseError);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(exception.getMessage());
        }
    }
}
