package com.playready.PlayReadyBackend.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name ="contacts")
public class Contract {
    @Id
    @GeneratedValue
    private long id;
    private Date start;
    private Long months;
    private Double price;
    @ManyToOne
    private User renter;
    private byte[] file;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Long getMonths() {
        return months;
    }

    public void setMonths(Long months) {
        this.months = months;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public User getRenter() {
        return renter;
    }

    public void setRenter(User renter) {
        this.renter = renter;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }
}

