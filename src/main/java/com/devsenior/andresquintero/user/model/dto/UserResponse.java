package com.devsenior.andresquintero.user.model.dto;

import java.util.List;

import com.devsenior.andresquintero.user.model.shared.Role;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserResponse {
    private String id;
    private String username;

    private String password;
    private String email;
    private List<Role> roles;

    private String avatar;

    private String colortheme;

    private boolean active;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @JsonProperty("color_theme")
    public String getColortheme() {
        return colortheme;
    }

    public void setColortheme(String colortheme) {
        this.colortheme = colortheme;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

}
