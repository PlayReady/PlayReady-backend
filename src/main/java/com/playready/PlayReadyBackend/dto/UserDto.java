package com.playready.PlayReadyBackend.dto;

import java.util.List;

public class UserDto {
    public String username;
    public String password;
    public String phonenumber;
    public String email;
    public List<String> roles;
    public List<Long> requestedproductsid;
}
