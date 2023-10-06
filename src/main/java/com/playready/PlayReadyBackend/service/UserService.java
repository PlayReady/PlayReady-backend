package com.playready.PlayReadyBackend.service;

import com.playready.PlayReadyBackend.dto.UserDto;
import com.playready.PlayReadyBackend.model.Role;
import com.playready.PlayReadyBackend.model.User;
import com.playready.PlayReadyBackend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Iterable<UserDto> getAllUsers() {
        Iterable<User> users = userRepository.findAll();
        List<UserDto> userDtoList = new ArrayList<>();

        for (User user : users) {
            userDtoList.add(convertToDto(user));
        }
        return userDtoList;
    }


    private UserDto convertToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.username = user.getUsername();
        List<Role> roles = user.getRoles();
        List<String> roleNames= new ArrayList<>();
        for(Role role:roles){
            roleNames.add(role.getRolename());
        }
        userDto.roles = roleNames;
        userDto.password = user.getPassword();
        return userDto;
    }

}
