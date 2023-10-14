package com.playready.PlayReadyBackend.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {

    @Test
    public void testUserGettersAndSetters() {
        User user = new User();

        user.setUsername("testuser");
        user.setPassword("password");
        user.setPhonenumber("1234567890");
        user.setEmail("testuser@example.com");

        assertEquals("testuser", user.getUsername());
        assertEquals("password", user.getPassword());
        assertEquals("1234567890", user.getPhonenumber());
        assertEquals("testuser@example.com", user.getEmail());
    }

    @Test
    public void testRoleAssociation() {
        User user = new User();
        Role role = new Role(); // Create a Role object for testing
        user.setRoles(List.of(role));

        assertEquals(1, user.getRoles().size());
        assertEquals(role, user.getRoles().get(0));
    }

    @Test
    public void testRequestedProductsAssociation() {
        User user = new User();
        Product product = new Product(); // Create a Product object for testing
        user.setRequestedProducts(List.of(product));

        assertEquals(1, user.getRequestedProducts().size());
        assertEquals(product, user.getRequestedProducts().get(0));
    }
}
