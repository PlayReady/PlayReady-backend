package com.playready.PlayReadyBackend.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class AuthDto {

    @Schema(description = "Username for authentication", example = "gebruiker")
    public String username;

    @Schema(description = "Password for authentication", example = "gebruiker")
    public String password;
}
