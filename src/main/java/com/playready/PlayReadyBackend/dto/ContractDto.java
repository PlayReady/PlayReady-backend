package com.playready.PlayReadyBackend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;

import java.util.Date;
import java.util.List;

public class ContractDto {

    @Schema(example = "1", description = "Unique identifier of the contract")
    public long id;

    @Schema(example = "2025-06-26T00:00:00.000+00:00", description = "Contract start date")
    public Date start;

    @Min(1)
    @Schema(example = "12", description = "Duration of the contract in months, must be at least 1 month")
    public Long months;

    @Positive
    @Schema(example = "1000.50", description = "Price of the contract, must be a positive number")
    public Double price;

    @Schema(example = "John Doe", description = "Name of the renter")
    public String renter;

    @Schema(example = "[101, 102]", description = "List of related invoice IDs")
    public List<Long> invoiceIds;

    @Schema(description = "File associated with the contract (binary data)")
    public byte[] file;
}
