package com.example.demo.controller;


import com.example.demo.exception.UserAlreadyExistsException;
import com.example.demo.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;
import java.util.Map;

@ControllerAdvice
@RestController
public class HexceptionHandler {

    private static final String MESSAGE_KEY = "message";

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public Map<String, String> handleBookingOverlapException(IllegalArgumentException e) {
        return Map.of(
                MESSAGE_KEY, e.getMessage()
        );
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NoSuchAlgorithmException.class)
    public Map<String, String> handleBookingOverlapException(NoSuchAlgorithmException e) {
        return Map.of(
                MESSAGE_KEY, e.getMessage()
        );
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserNotFoundException.class)
    public Map<String, String> handleBookingOverlapException(UserNotFoundException e) {
        return Map.of(
                MESSAGE_KEY, e.getMessage()
        );
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserAlreadyExistsException.class)
    public Map<String, String> handleBookingOverlapException(UserAlreadyExistsException e) {
        return Map.of(
                MESSAGE_KEY, e.getMessage()
        );
    }
}
