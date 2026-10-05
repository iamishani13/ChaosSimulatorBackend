package com.example.ChaosSimulator.exception;

public class DeadEntityException extends RuntimeException{
    public DeadEntityException(String message){
        super(message);
    }
}
