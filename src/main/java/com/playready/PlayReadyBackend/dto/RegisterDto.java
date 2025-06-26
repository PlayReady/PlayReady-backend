package com.playready.PlayReadyBackend.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class RegisterDto {

    @Schema(example = "newuser", description = "Desired username for registration")
    public String username;

    @Schema(example = "SecurePass123!", description = "Desired password for registration")
    public String password;

    @Schema(example = "+1234567890", description = "User's phone number")
    public String phonenumber;

    @Schema(example = "newuser@example.com", description = "User's email address")
    public String email;
}
