package com.example.demo.exception;

import java.util.NoSuchElementException;

public class HallNotFoundException extends NoSuchElementException {

    public HallNotFoundException(String message) {
        super(message);
    }
}
