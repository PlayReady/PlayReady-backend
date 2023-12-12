package com.playready.PlayReadyBackend.controler;

import com.playready.PlayReadyBackend.dto.InvoiceDto;
import com.playready.PlayReadyBackend.service.InvoiceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.time.Month;
import java.time.Year;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {

    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping
    public ResponseEntity<Iterable<InvoiceDto>> getInvoices() {
        return ResponseEntity.ok(invoiceService.getAllInvoices());
    }

    @GetMapping("/{id}")
    public ResponseEntity<InvoiceDto> getInvoice(@PathVariable long id) {
        return ResponseEntity.ok(invoiceService.getInvoice(id));
    }

    @PostMapping
    public ResponseEntity<InvoiceDto> createInvoice(
            @RequestParam Year year,
            @RequestParam Month month,
            @RequestParam Double price,
            @RequestParam MultipartFile file
    ) {
        InvoiceDto invoiceDto = new InvoiceDto();
        try{
            invoiceDto.year = year;
            invoiceDto.month = month;
            invoiceDto.price = price;
            invoiceDto.file = file.getBytes();
        }catch (IOException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        invoiceDto = invoiceService.createInvoice(invoiceDto);
        URI uri = URI.create(ServletUriComponentsBuilder
                .fromCurrentRequest().path("/" + invoiceDto.id).toUriString());
        return ResponseEntity.created(uri).body(invoiceDto);
    }

    @GetMapping("/{id}/file")
    public ResponseEntity<byte[]> getFile(@PathVariable long id) {
        return ResponseEntity.ok(invoiceService.getFile(id));
    }
}