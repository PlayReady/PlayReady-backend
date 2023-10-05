package com.playready.PlayReadyBackend.service;
import com.playready.PlayReadyBackend.dto.ProductDto;
import com.playready.PlayReadyBackend.model.Product;
import com.playready.PlayReadyBackend.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository repos;

    public ProductService(ProductRepository repos) {
        this.repos = repos;
    }
    public Iterable<ProductDto> getAllProduct() {
        Iterable<Product> products = repos.findAll();
        List<ProductDto> productDtos = new ArrayList<>();

        for(Product product:products) {
            ProductDto productDto =new ProductDto();
            productDto.id = product.getId();
            productDto.name=product.getName();
            productDto.price=product.getPrice();
            productDtos.add(productDto);
        }
        return productDtos;
    }
}
