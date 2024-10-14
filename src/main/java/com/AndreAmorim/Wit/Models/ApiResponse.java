package com.AndreAmorim.Wit.Models;

public class ApiResponse<T> {

    private Boolean Successfull = true;
    private T Data = null;
    private ApiResponseDetails ErrorDetails = null;


    public Boolean getSuccessfull() {
        return Successfull;
    }

    public void setSuccessfull(Boolean successfull) {
        Successfull = successfull;
    }

    public T getData() {
        return Data;
    }

    public void setData(T data) {
        Data = data;
    }

    public ApiResponseDetails getErrorDetails() {
        return ErrorDetails;
    }

    public void setErrorDetails(ApiResponseDetails errorDetails) {
        ErrorDetails = errorDetails;
    }
}



