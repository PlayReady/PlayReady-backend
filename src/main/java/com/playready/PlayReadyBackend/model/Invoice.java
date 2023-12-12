package com.playready.PlayReadyBackend.model;

import jakarta.persistence.*;

import java.time.Month;
import java.time.Year;

@Entity
@Table(name= "invoices")
public class Invoice {
    @Id
    @GeneratedValue
    private long id;
    private Year year;
    private Month month;

    public void setId(long id) {
        this.id = id;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    private double price;
    private boolean paid;
    private byte[] file;
    @ManyToOne
    private Contract contract;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Year getYear() {
        return year;
    }

    public void setYear(Year year) {
        this.year = year;
    }

    public Month getMonth() {
        return month;
    }

    public void setMonth(Month month) {
        this.month = month;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }
}
