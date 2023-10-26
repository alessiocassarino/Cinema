package com.example.demo.exception;

import java.util.NoSuchElementException;

public class FilmNotFoundException extends NoSuchElementException {

    public FilmNotFoundException(String message) {
        super(message);
    }
}
