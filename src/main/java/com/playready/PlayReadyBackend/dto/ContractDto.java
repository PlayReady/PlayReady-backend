package com.playready.PlayReadyBackend.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;


import java.util.Date;
import java.util.List;

public class ContractDto {

    public long id;
    public Date start;
    @Min(1)
    public Long months;
    @Positive
    public Double price;
    public String renter;
    public List<Long> invoiceIds;
    public byte[] file;
}
