package org.example.api.controller;

import org.example.api.dto.ContractTemplateDto;
import org.example.api.service.ContractTemplateService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contractTemplates")
public class ContractTemplateController {

    private final ContractTemplateService contractTemplateService;

    public ContractTemplateController(ContractTemplateService contractTemplateService) {
        this.contractTemplateService = contractTemplateService;
    }

    @GetMapping("/{id}")
    public ContractTemplateDto getOne(@PathVariable("id") Long id) {
        return contractTemplateService.getContractTemplate(id);
    }

    @GetMapping
    public List<ContractTemplateDto> getList() {
        return contractTemplateService.getContractTemplates();
    }

    @PostMapping
    public ContractTemplateDto save(@RequestBody ContractTemplateDto req) {
        return contractTemplateService.save(req);
    }
}
