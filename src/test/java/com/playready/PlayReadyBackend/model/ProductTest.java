package com.playready.PlayReadyBackend.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProductTest {

    @Test
    public void testProductGettersAndSetters() {
        Product product = new Product();

        product.setId(1L);
        product.setName("Sample Product");
        product.setPrice(1000L);
        product.setFeatured(true);

        assertEquals(1L, product.getId());
        assertEquals("Sample Product", product.getName());
        assertEquals(1000L, product.getPrice());
        assertTrue(product.isFeatured());
    }

    @Test
    public void testImage() {
        Product product = new Product();
        byte[] imageData = new byte[]{1, 2, 3};
        product.setImage(imageData);
        assertArrayEquals(imageData, product.getImage());
    }
}
