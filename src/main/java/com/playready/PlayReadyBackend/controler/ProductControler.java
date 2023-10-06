package com.playready.PlayReadyBackend.controler;

import com.playready.PlayReadyBackend.dto.ProductDto;
import com.playready.PlayReadyBackend.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;


@RestController
@RequestMapping("/products")
public class ProductControler {

    private final ProductService service;

    public ProductControler(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Iterable<ProductDto>> getProducts() {
        return ResponseEntity.ok(service.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable Long id) {
        return ResponseEntity.ok(service.getProduct(id));

    }

    @GetMapping("/featured")
    public ResponseEntity<Iterable<ProductDto>> getFeaturedProducts() {
        return ResponseEntity.ok(service.getFeaturedProducts());
    }

    @PostMapping
    public ResponseEntity<Object> createTeacher(@Valid @RequestBody ProductDto productDto, BindingResult br) {
        if (br.hasFieldErrors()) {
            StringBuilder sb = new StringBuilder();
            for (FieldError fe : br.getFieldErrors()) {
                sb.append(fe.getField() + ": ");
                sb.append(fe.getDefaultMessage());
                sb.append("\n");
            }
            return ResponseEntity.badRequest().body((sb.toString()));
        } else {
            Long NewId = service.createProduct(productDto);

            URI uri = URI.create(ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/" + NewId).toUriString());
            return ResponseEntity.created(uri).body(uri);
        }
    }

    @PostMapping("/{id}/image")
    public ResponseEntity<Object> uploadProductImage(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
       try {
           service.uploadImage(id, file);

           URI uri = URI.create(ServletUriComponentsBuilder
                   .fromCurrentRequest().toUriString());
           return ResponseEntity.created(uri).body(uri);
       } catch (Exception e) {
           return ResponseEntity.internalServerError().body("Something went wrong.");
       }
    }
}
