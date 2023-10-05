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

    public Iterable<ProductDto> getAllProducts() {
        Iterable<Product> products = repos.findAll();
        List<ProductDto> productDtos = new ArrayList<>();

        for (Product product : products) {
            productDtos.add(convertToDto(product));
        }
        return productDtos;
    }

    public ProductDto getProduct(long id) {
        Product product = repos.findById(id).orElseThrow(() -> null);
        return convertToDto(product);
    }

    public Long createProduct(ProductDto productDto) {
        Product product = convertToEntity(productDto);
        repos.save(product);
        return product.getId();
    }

    private ProductDto convertToDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.id = product.getId();
        productDto.name = product.getName();
        productDto.price = product.getPrice();
        productDto.featured =product.isFeatured();
        return productDto;
    }

    private Product convertToEntity(ProductDto productDto) {
        Product product = new Product();
        product.setName(productDto.name);
        product.setPrice(productDto.price);
        product.setFeatured(productDto.featured);
        return product;
    }
}
