package com.playready.PlayReadyBackend.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name="users")
public class User {
    @Id
    private String username;
    private String password;
    private String phonenumber;
    private String email;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles;
    @ManyToMany
    private List<Product> requestedProducts;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Product> getRequestedProducts() {
        return requestedProducts;
    }

    public void setRequestedProducts(List<Product> requestedProducts) {
        this.requestedProducts = requestedProducts;
    }
}
