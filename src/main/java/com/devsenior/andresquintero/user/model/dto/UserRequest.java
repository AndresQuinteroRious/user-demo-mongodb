package com.devsenior.andresquintero.user.model.dto;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.devsenior.andresquintero.user.model.shared.Role;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;





public class UserRequest {

    @NotBlank(message="el nombre de usuario es obligatorio")
    private String username;
   
    @NotBlank(message="la contraseña es obligatoria")
    @Length(min = 6, message="la contraseña debe tener al menos 6 caracteres")
    private String password;
   
    @NotBlank(message="el correo electrónico es obligatorio")
    @Email(message="el correo electrónico no es válido")
    private String email;
   
    @NotEmpty(message="los roles son obligatorios")
    private List<Role> roles;

    private String avatar;

    @JsonProperty("color_theme")
    private String colortheme;

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
            public String getColortheme() {
                return colortheme;
            }
            public void setColortheme(String colortheme) {
                this.colortheme = colortheme;
            }
           

            
}
