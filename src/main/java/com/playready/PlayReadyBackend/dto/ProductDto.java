package com.playready.PlayReadyBackend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public class ProductDto {

    @Schema(example = "101", description = "Unique identifier of the product")
    public Long id;

    @NotBlank
    @Schema(example = "Premium Office Chair", description = "Name of the product")
    public String name;

    @Schema(example = "299", description = "Price of the product in USD")
    public long price;

    @Schema(example = "true", description = "Whether the product is featured or not")
    public boolean featured;

    @Schema(description = "Image of the product (binary data)")
    public byte[] image;
}
