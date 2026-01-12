package com.devsenior.andresquintero.user.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsenior.andresquintero.user.model.dto.UserRequest;
import com.devsenior.andresquintero.user.model.dto.UserResponse;
import com.devsenior.andresquintero.user.service.UserService;
import com.devsenior.andresquintero.user.exception.UserAlreadyExistsException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;




@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserResponse> getAllUsers() {

        return userService.getAll();
    }
    @GetMapping("/{id}")
    public UserResponse getUserById(@PathVariable String id) {
        return userService.getById(id);
    }

    @GetMapping("/find")
    public UserResponse findUsersByParams(@RequestParam String username) {
        return userService.getByUsername(username);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public UserResponse createUser(@RequestBody UserRequest user) throws UserAlreadyExistsException {
        return userService.save(user);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable String id) {
        userService.delete(id);
    }
}