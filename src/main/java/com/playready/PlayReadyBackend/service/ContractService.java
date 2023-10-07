package com.playready.PlayReadyBackend.service;

import com.playready.PlayReadyBackend.dto.ContractDto;
import com.playready.PlayReadyBackend.model.Contract;
import com.playready.PlayReadyBackend.model.Invoice;
import com.playready.PlayReadyBackend.model.User;
import com.playready.PlayReadyBackend.repository.ContractRepository;
import com.playready.PlayReadyBackend.repository.InvoiceRepository;
import com.playready.PlayReadyBackend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ContractService {
    private final ContractRepository contractRepository;
    private final UserRepository userRepository;
    private final InvoiceRepository invoiceRepository;

    public ContractService(ContractRepository contractRepository, UserRepository userRepository, InvoiceRepository invoiceRepository) {
        this.contractRepository = contractRepository;
        this.userRepository =userRepository;
        this.invoiceRepository =invoiceRepository;
    }

    public ContractDto getContract(Long id) {
        Contract contract = contractRepository.findById(id).orElseThrow(() -> null);
        return convertToDto(contract);
    }

    public Long createContract(ContractDto contractDto) {
        Contract contract = convertToEntity(contractDto);
        contractRepository.save(contract);
        return contract.getId();
    }


    private ContractDto convertToDto(Contract contract) {
        ContractDto contractDto = new ContractDto();
        contractDto.id = contract.getId();
        contractDto.start = contract.getStart();
        contractDto.months = contract.getMonths();
        contractDto.price = contract.getPrice();

        User renter = contract.getRenter();
        if (renter != null) {
            contractDto.renter = renter.getUsername();
        }

        contractDto.file = contract.getFile();

        List<Invoice> invoices = contract.getInvoices();
        if (invoices != null) {
            contractDto.invoiceIds = new ArrayList<>();
            for (Invoice invoice : invoices) {
                contractDto.invoiceIds.add(invoice.getId());
            }
        }

        return contractDto;
    }



    private Contract convertToEntity(ContractDto contractDto) {
        Contract contract = new Contract();
        contract.setStart(contractDto.start);
        contract.setMonths(contractDto.months);
        contract.setPrice(contractDto.price);

        if (contractDto.renter != null) {
            Optional<User> renter = userRepository.findById(contractDto.renter);
            if (renter.isPresent()) {
                contract.setRenter(renter.get());
            }
        }

        if (contractDto.invoiceIds != null) {
            List<Invoice> invoices = new ArrayList<>();
            for (Long invoiceId : contractDto.invoiceIds) {
                Optional<Invoice> invoice = invoiceRepository.findById(invoiceId);
                invoice.ifPresent(invoices::add);
            }
            contract.setInvoices(invoices);
        }

        contract.setFile(contractDto.file);

        return contract;
    }

}
