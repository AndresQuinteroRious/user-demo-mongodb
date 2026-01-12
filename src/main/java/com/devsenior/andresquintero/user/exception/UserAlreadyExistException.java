package com.devsenior.andresquintero.user.exception;

public class UserAlreadyExistException extends RuntimeException {

    public UserAlreadyExistException(String message) {
        super("el nombre de usuario ya existe");
    }
}
