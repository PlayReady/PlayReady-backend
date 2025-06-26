package com.playready.PlayReadyBackend.loader;

import com.playready.PlayReadyBackend.model.Product;
import com.playready.PlayReadyBackend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.util.Optional;

@Component
@Profile("dev")
public class ImageLoader implements ApplicationRunner {

    @Autowired
    private ProductRepository productRepository;

    public void run(ApplicationArguments args) throws Exception {
        ClassPathResource resource = new ClassPathResource("static/images/product.png");
        byte[] imageBytes = Files.readAllBytes(resource.getFile().toPath());

        for (long productId = 1L; productId <= 3L; productId++) {
            Optional<Product> productOptional = productRepository.findById(productId);
            productOptional.ifPresent(product -> {
                product.setImage(imageBytes);
                productRepository.save(product);
            });
        }
    }

}
