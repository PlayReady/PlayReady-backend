package com.playready.PlayReadyBackend.dto;

import java.time.Month;
import java.time.Year;

public class InvoiceDto {
    public long id;
    public Year year;
    public Month month;
    public double price;
    public boolean paid;
    public byte[] file;
    public Long contractId;
}