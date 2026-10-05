package com.example.ChaosSimulator.exception;

public class InsufficientEnergyException extends RuntimeException{
    public InsufficientEnergyException(String message){
        super(message);
    }
}
