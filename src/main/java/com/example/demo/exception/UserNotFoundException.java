package com.example.demo.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException() {
        super("Username o password errati");
    }
}
