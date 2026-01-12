package com.devsenior.andresquintero.user.mapper;



import org.springframework.stereotype.Component;

import com.devsenior.andresquintero.user.model.document.User;
import com.devsenior.andresquintero.user.model.document.User.Configuration;
import com.devsenior.andresquintero.user.model.dto.UserRequest;
import com.devsenior.andresquintero.user.model.dto.UserResponse;

@Component
public class Usermapper {

    public User toDocument(UserRequest info){
        var response = new User();
        response.setUsername(info.getUsername());
        response.setPassword(info.getPassword());
        response.setEmail(info.getEmail());
        response.setRoles(info.getRoles());

        var  configuration=new Configuration();
        configuration.setAvatar(info.getAvatar());
        configuration.setColorTheme(info.getColortheme());
        configuration.setActive( true);

        response.setConfiguration(configuration);
        return response;
    }

    public UserResponse toResponse(User info){
        var response = new UserResponse();

        response.setId(info.getId());
        response.setUsername(info.getUsername());
        response.setEmail(info.getEmail());
        response.setRoles(info.getRoles());
        response.setAvatar(info.getConfiguration().getAvatar());
        response.setColortheme(info.getConfiguration().getColorTheme());
        response.setActive(info.getConfiguration().getActive());
        return response;
    }
}
