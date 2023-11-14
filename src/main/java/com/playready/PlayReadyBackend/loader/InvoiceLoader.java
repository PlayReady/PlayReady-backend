package com.playready.PlayReadyBackend.loader;

import com.playready.PlayReadyBackend.repository.ProductRepository;
import jakarta.persistence.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class InvoiceLoader implements ApplicationRunner {

    @Autowired
    private ProductRepository productRepository;

    public void run(ApplicationArguments args) throws Exception {
        for (long invoiceId = 1L; invoiceId <= 3L; invoiceId++) {

        }
    }
}
