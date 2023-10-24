package com.example.demo.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String name) {
        super("L'utente di nome :  " + name + " non è stato trovato");
    }
}
