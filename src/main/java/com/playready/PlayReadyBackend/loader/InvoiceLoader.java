package com.playready.PlayReadyBackend.loader;

import com.playready.PlayReadyBackend.model.Invoice;
import com.playready.PlayReadyBackend.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.time.Month;
import java.time.Year;

@Component
public class InvoiceLoader implements ApplicationRunner {

    @Autowired
    private InvoiceRepository invoiceRepository;

    public void run(ApplicationArguments args) throws Exception {
        for (long i = 0; i <= 2; i++) {
            Invoice invoice = new Invoice();
            invoice.setYear(Year.of(2023));
            invoice.setMonth(Month.of(9));
            invoice.setPrice(120);
            ClassPathResource resource = new ClassPathResource("static/invoices/invoice.pdf");
            invoice.setFile(Files.readAllBytes(resource.getFile().toPath()));
            invoiceRepository.save(invoice);
        }
    }
}
