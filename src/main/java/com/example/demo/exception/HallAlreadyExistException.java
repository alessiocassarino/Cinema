package com.example.demo.exception;

public class HallAlreadyExistException extends RuntimeException {

    public HallAlreadyExistException(String message) {
        super(message);
    }
}
