package org.example.api.controller;

import org.example.api.dto.ContractDto;
import org.example.api.service.ContractService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contracts")
public class ContractController {
    private final ContractService contractService;

    public ContractController(ContractService contractService) {
        this.contractService = contractService;
    }

    @GetMapping("/{id}")
    public ContractDto getOne(@PathVariable("id") Long id) {
        return contractService.getContract(id);
    }

    @GetMapping
    public Page<ContractDto> getList(Pageable pageable) {
        return contractService.getContracts(pageable);
    }

    @PostMapping
    public ContractDto save(@RequestBody ContractDto req) {
        return contractService.save(req);
    }
}
