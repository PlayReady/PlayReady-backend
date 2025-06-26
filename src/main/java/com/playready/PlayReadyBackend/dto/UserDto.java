package com.playready.PlayReadyBackend.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public class UserDto {

    @Schema(
            example = "john_doe",
            description = "Unique username of the user"
    )
    public String username;

    @Schema(
            example = "P@ssw0rd123",
            description = "User's password (should be encrypted in storage)"
    )
    public String password;

    @Schema(
            example = "+1234567890",
            description = "User's phone number in international format"
    )
    public String phonenumber;

    @Schema(
            example = "john@example.com",
            description = "User's email address"
    )
    public String email;

    @Schema(
            example = "[\"USER\", \"ADMIN\"]",
            description = "List of roles assigned to the user"
    )
    public List<String> roles;

    @Schema(
            example = "[101, 102, 103]",
            description = "List of requested product IDs by the user"
    )
    public List<Long> requestedproductsid;
}
