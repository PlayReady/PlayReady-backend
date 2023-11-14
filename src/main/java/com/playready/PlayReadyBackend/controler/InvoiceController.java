package com.playready.PlayReadyBackend.controler;

import com.playready.PlayReadyBackend.dto.InvoiceDto;
import com.playready.PlayReadyBackend.service.InvoiceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

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
    public ResponseEntity<InvoiceDto> createInvoice(@RequestBody InvoiceDto invoiceDto) {
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