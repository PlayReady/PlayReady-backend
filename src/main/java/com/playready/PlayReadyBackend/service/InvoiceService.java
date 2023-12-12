package com.playready.PlayReadyBackend.service;

import com.playready.PlayReadyBackend.dto.InvoiceDto;
import com.playready.PlayReadyBackend.model.Contract;
import com.playready.PlayReadyBackend.model.Invoice;
import com.playready.PlayReadyBackend.repository.ContractRepository;
import com.playready.PlayReadyBackend.repository.InvoiceRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class InvoiceService {
    private final InvoiceRepository invoiceRepository;
    private final ContractRepository contractRepository;

    public InvoiceService(
            InvoiceRepository invoiceRepository,
            ContractRepository contractRepository
    ) {
        this.invoiceRepository = invoiceRepository;
        this.contractRepository = contractRepository;
    }

    public InvoiceDto getInvoice(long id) {
        Invoice invoice = invoiceRepository.findById(id).orElseThrow(() -> null);
        return convertToDto(invoice);
    }

    public byte[] getFile(long id){
        return invoiceRepository.findFileById(id);
    }


    public Iterable<InvoiceDto> getAllInvoices() {
        List<InvoiceDto> invoiceDtos = new ArrayList<>();
        Iterable<Invoice> invoices = invoiceRepository.findAll();
        for (Invoice invoice : invoices) {
            invoiceDtos.add(convertToDto(invoice));
        }
        return invoiceDtos;
    }

    public InvoiceDto createInvoice(InvoiceDto invoiceDto) {
        Invoice invoice = convertToEntity(invoiceDto);
        invoiceDto.id = invoice.getId();
        invoiceRepository.save(invoice);
        return invoiceDto;
    }


    private InvoiceDto convertToDto(Invoice invoice) {
        InvoiceDto invoiceDto = new InvoiceDto();
        invoiceDto.id = invoice.getId();
        invoiceDto.month = invoice.getMonth();
        invoiceDto.year = invoice.getYear();
        invoiceDto.paid = invoice.isPaid();
        invoiceDto.price = invoice.getPrice();
        invoiceDto.file = invoice.getFile();
        Contract contract = invoice.getContract();
        if(contract !=null){
            invoiceDto.contractId=contract.getId();
        }

        return invoiceDto;
    }

    private Invoice convertToEntity(InvoiceDto invoiceDto) {
        Invoice invoice = new Invoice();
        invoice.setId(invoiceDto.id);
        invoice.setYear(invoiceDto.year);
        invoice.setMonth(invoiceDto.month);
        invoice.setPrice(invoiceDto.price);
        invoice.setPaid(invoiceDto.paid);
        invoice.setFile(invoiceDto.file);
        if(invoiceDto.contractId != null) {
            invoice.setContract(contractRepository.findById(invoiceDto.contractId).orElseThrow(() -> null));
        }
        return invoice;
    }
}