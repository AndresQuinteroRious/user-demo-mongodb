/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.devsenior.andresquintero.user.service;


public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException() {
    }

    public UserAlreadyExistsException(String string) {
        super(string);
    }

}
