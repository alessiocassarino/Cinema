package com.example.demo.controller;


import com.example.demo.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;
import java.util.Map;


@ControllerAdvice
@RestController
public class ExceptionHandler {

    private static final String MESSAGE_KEY = "message";

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @org.springframework.web.bind.annotation.ExceptionHandler(IllegalArgumentException.class)
    public Map<String, String> handleBookingOverlapException(IllegalArgumentException e) {
        return Map.of(
                MESSAGE_KEY, e.getMessage()
        );
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @org.springframework.web.bind.annotation.ExceptionHandler(NoSuchAlgorithmException.class)
    public Map<String, String> handleBookingOverlapException(NoSuchAlgorithmException e) {
        return Map.of(
                MESSAGE_KEY, e.getMessage()
        );
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @org.springframework.web.bind.annotation.ExceptionHandler(UserNotFoundException.class)
    public Map<String, String> handleBookingOverlapException(UserNotFoundException e) {
        return Map.of(
                MESSAGE_KEY, e.getMessage()
        );
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @org.springframework.web.bind.annotation.ExceptionHandler(UserAlreadyExistsException.class)
    public Map<String, String> handleBookingOverlapException(UserAlreadyExistsException e) {
        return Map.of(
                MESSAGE_KEY, e.getMessage()
        );
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @org.springframework.web.bind.annotation.ExceptionHandler(FilmNotFoundException.class)
    public Map<String, String> handleBookingOverlapException(FilmNotFoundException e) {
        return Map.of(
                MESSAGE_KEY, e.getMessage()
        );
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @org.springframework.web.bind.annotation.ExceptionHandler(HallNotFoundException.class)
    public Map<String, String> handleBookingOverlapException(HallNotFoundException e) {
        return Map.of(
                MESSAGE_KEY, e.getMessage()
        );
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @org.springframework.web.bind.annotation.ExceptionHandler(SchedulingNotFoundException.class)
    public Map<String, String> handleBookingOverlapException(SchedulingNotFoundException e) {
        return Map.of(
                MESSAGE_KEY, e.getMessage()
        );
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @org.springframework.web.bind.annotation.ExceptionHandler(HallAlreadyExistException.class)
    public Map<String, String> handleBookingOverlapException(HallAlreadyExistException e) {
        return Map.of(
                MESSAGE_KEY, e.getMessage()
        );
    }
}
