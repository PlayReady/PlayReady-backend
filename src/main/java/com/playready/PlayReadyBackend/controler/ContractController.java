package com.playready.PlayReadyBackend.controler;


import com.playready.PlayReadyBackend.dto.ContractDto;
import com.playready.PlayReadyBackend.service.ContractService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/contracts")
public class ContractController {

    private final ContractService service;

    public ContractController(ContractService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContractDto> getContract(@PathVariable Long id) {
        return ResponseEntity.ok(service.getContract(id));
    }

    @PostMapping
    public ResponseEntity<Object> createContract(@Valid @RequestBody ContractDto contractDto, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            StringBuilder sb = new StringBuilder();
            for (FieldError fe : bindingResult.getFieldErrors()) {
                sb.append(fe.getField())
                        .append(": ")
                        .append(fe.getDefaultMessage())
                        .append("\n");
            }
            return ResponseEntity.badRequest().body((sb.toString()));
        } else {
            Long NewId = service.createContract(contractDto);

            URI uri = URI.create(ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/" + NewId).toUriString());
            return ResponseEntity.created(uri).body(uri);
        }
    }
}
