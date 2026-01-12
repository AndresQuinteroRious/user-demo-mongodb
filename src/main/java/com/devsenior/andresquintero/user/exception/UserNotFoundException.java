package com.devsenior.andresquintero.user.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException() {
        this("el usuario no fue encontrado");
    }

    public UserNotFoundException(String message) {
        super(message);
    }

}
