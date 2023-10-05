package com.playready.PlayReadyBackend.controler;

import com.playready.PlayReadyBackend.dto.ProductDto;
import com.playready.PlayReadyBackend.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductControler {

    private final ProductService service;

    public ProductControler(ProductService service) {
        this.service = service;
    }
    @GetMapping
    public ResponseEntity<Iterable<ProductDto>> getProducts() {
        return ResponseEntity.ok(service.getAllProduct());
    }
}
