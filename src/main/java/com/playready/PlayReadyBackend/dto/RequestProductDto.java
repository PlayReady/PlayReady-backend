package com.playready.PlayReadyBackend.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class RequestProductDto {

   @Schema(
           example = "[1, 2, 3]",
           description = "Array of product IDs being requested"
   )
   public Long[] id;
}
