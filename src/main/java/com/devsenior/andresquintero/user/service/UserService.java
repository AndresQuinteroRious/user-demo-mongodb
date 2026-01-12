package com.devsenior.andresquintero.user.service;

import java.util.List;

import com.devsenior.andresquintero.user.model.dto.UserRequest;
import com.devsenior.andresquintero.user.model.dto.UserResponse;
import com.devsenior.andresquintero.user.exception.UserAlreadyExistsException;

public interface UserService {

    UserResponse save(UserRequest user) throws UserAlreadyExistsException;

List<UserResponse> findAll();

UserResponse findById(String id);

void delete(String id);

UserResponse getByUsername (String username);

List<UserResponse> getAll();

UserResponse getById(String id);

}
