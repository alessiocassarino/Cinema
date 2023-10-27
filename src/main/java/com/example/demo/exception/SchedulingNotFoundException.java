package com.example.demo.exception;

import java.util.NoSuchElementException;

public class SchedulingNotFoundException extends NoSuchElementException {
    public SchedulingNotFoundException(String message) {
        super(message);
    }
}
