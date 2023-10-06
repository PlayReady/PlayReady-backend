package com.playready.PlayReadyBackend.service;

import com.playready.PlayReadyBackend.dto.ContractDto;
import com.playready.PlayReadyBackend.model.Contract;
import com.playready.PlayReadyBackend.model.User;
import com.playready.PlayReadyBackend.repository.ContractRepository;
import com.playready.PlayReadyBackend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ContractService {
    private final ContractRepository contractRepository;
    private final UserRepository userRepository;

    public ContractService(ContractRepository contractRepository, UserRepository userRepository) {
        this.contractRepository = contractRepository;
        this.userRepository =userRepository;
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
        User renter =contract.getRenter();
        if(renter!=null){
            contractDto.renter = contract.getRenter().getUsername();
        }
        contractDto.file = contract.getFile();
        return contractDto;
    }

    private Contract convertToEntity(ContractDto contractDto) {
        Contract contract = new Contract();
        contract.setStart(contractDto.start);
        contract.setMonths(contractDto.months);
        contract.setPrice(contractDto.price);
        if(contractDto.renter!=null){
            Optional<User> renter = userRepository.findById(contractDto.renter);
            if(renter.isPresent()){contract.setRenter(renter.get());}
        }

        contract.setFile(contractDto.file);
        return contract;
    }
}
