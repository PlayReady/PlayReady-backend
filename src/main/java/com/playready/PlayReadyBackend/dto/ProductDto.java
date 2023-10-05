package com.playready.PlayReadyBackend.dto;

import jakarta.validation.constraints.NotBlank;

public class ProductDto {
    public Long id;
    @NotBlank
    public String name;
    public long price;
}
