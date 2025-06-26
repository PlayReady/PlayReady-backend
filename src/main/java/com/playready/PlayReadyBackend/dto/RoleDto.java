package com.playready.PlayReadyBackend.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class RoleDto {

    @Schema(
            example = "ADMIN",
            description = "Name of the role assigned to a user"
    )
    public String rolename;
}
