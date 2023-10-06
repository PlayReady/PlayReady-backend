package com.playready.PlayReadyBackend.service;

import com.playready.PlayReadyBackend.dto.ProductDto;
import com.playready.PlayReadyBackend.model.Product;
import com.playready.PlayReadyBackend.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public Iterable<ProductDto> getFeaturedProducts() {
        Iterable<Product> Products = repos.findTop3ByFeaturedIsTrue();
        List<ProductDto> productDtos = new ArrayList<>();

        for (Product product : Products) {
            productDtos.add(convertToDto(product));
        }
        return productDtos;
    }

    public Long createProduct(ProductDto productDto) {
        Product product = convertToEntity(productDto);
        repos.save(product);
        return product.getId();
    }

    public void uploadImage(Long id, MultipartFile file) throws IOException {
            Optional<Product> productOptional = repos.findById(id);
            if (productOptional.isPresent() && !file.isEmpty()) {
                Product product = productOptional.get();
                product.setImage(file.getBytes());
                repos.save(product);
            } else {
                throw new IOException("product not found");
            }
    }

    private ProductDto convertToDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.id = product.getId();
        productDto.name = product.getName();
        productDto.price = product.getPrice();
        productDto.featured = product.isFeatured();
        productDto.image = product.getImage();
        return productDto;
    }

    private Product convertToEntity(ProductDto productDto) {
        Product product = new Product();
        product.setName(productDto.name);
        product.setPrice(productDto.price);
        product.setFeatured(productDto.featured);
        product.setImage(productDto.image);
        return product;
    }
}
