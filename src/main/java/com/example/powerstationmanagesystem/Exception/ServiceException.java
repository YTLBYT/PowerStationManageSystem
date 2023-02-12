package com.example.powerstationmanagesystem.Exception;


//自定义Service层返回的异常
public class ServiceException extends RuntimeException{
    public ServiceException(String message){
        super(message);
    }
}
