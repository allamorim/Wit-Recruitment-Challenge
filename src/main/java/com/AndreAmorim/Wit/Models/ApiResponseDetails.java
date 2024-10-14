package com.AndreAmorim.Wit.Models;

public class ApiResponseDetails
{
    private String ExceptionMessage;

    public ApiResponseDetails()
    {
        this.ExceptionMessage = null;
    }
    public ApiResponseDetails(String exceptionMessage)
    {
        this.ExceptionMessage = exceptionMessage;
    }

    public String getExceptionMessage() {
        return ExceptionMessage;
    }

    public void setExceptionMessage(String exceptionMessage) {
        ExceptionMessage = exceptionMessage;
    }

}
