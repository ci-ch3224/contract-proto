package org.example.api.controller;

import org.example.api.dto.ContractDto;
import org.example.api.service.ContractService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List<ContractDto> getList() {
        return contractService.getContracts();
    }

    @PostMapping
    public ContractDto save(@RequestBody ContractDto req) {
        return contractService.save(req);
    }
}
