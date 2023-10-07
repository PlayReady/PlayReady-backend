package com.playready.PlayReadyBackend.service;

import com.playready.PlayReadyBackend.dto.RegisterDto;
import com.playready.PlayReadyBackend.dto.UserDto;
import com.playready.PlayReadyBackend.model.User;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class AuthService {
    private final UserService userService;

    public AuthService(UserService userService) {
        this.userService = userService;
    }

    public String registerUser(@RequestBody RegisterDto registerDto){
        User user = userService.createUser(registrationToUser(registerDto));
        return user.getUsername();
    }
    private UserDto registrationToUser(RegisterDto registerDto){
        UserDto userDto = new UserDto();
        userDto.username= registerDto.username;
        userDto.password= registerDto.password;
        userDto.phonenumber= registerDto.phonenumber;
        userDto.email= registerDto.email;

        return userDto;
    }
}
