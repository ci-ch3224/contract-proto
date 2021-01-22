package org.example.api.controller;

import org.example.api.dto.ContractTemplateDto;
import org.example.api.service.ContractTemplateService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public Page<ContractTemplateDto> getList(Pageable pageable) {
        return contractTemplateService.getContractTemplates(pageable);
    }

    @PostMapping
    public ContractTemplateDto save(@RequestBody ContractTemplateDto req) {
        return contractTemplateService.save(req);
    }
}
