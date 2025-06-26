package com.playready.PlayReadyBackend.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.Month;
import java.time.Year;

public class InvoiceDto {

    @Schema(example = "1001", description = "Unique identifier of the invoice")
    public long id;

    @Schema(example = "2025", description = "Year the invoice is issued")
    public Year year;

    @Schema(example = "JUNE", description = "Month the invoice is issued")
    public Month month;

    @Schema(example = "500.00", description = "Price of the invoice")
    public double price;

    @Schema(example = "true", description = "Payment status of the invoice")
    public boolean paid;

    @Schema(description = "File associated with the invoice (binary data)")
    public byte[] file;

    @Schema(example = "1", description = "ID of the related contract")
    public Long contractId;
}
