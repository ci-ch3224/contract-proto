package org.example.api.service;

import org.example.api.domain.ContractTemplate;
import org.example.api.dto.ContractTemplateDto;
import org.example.api.repository.ContractTemplateRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ContractTemplateService {
    private final ContractTemplateRepository contractTemplateRepository;

    public ContractTemplateService(ContractTemplateRepository contractTemplateRepository) {
        this.contractTemplateRepository = contractTemplateRepository;
    }

    public ContractTemplateDto getContractTemplate(Long id) {
        return contractTemplateRepository.findById(id)
            .map(ContractTemplateDto::of)
            .orElseThrow(RuntimeException::new);
    }

    public List<ContractTemplateDto> getContractTemplates() {
        return StreamSupport.stream(contractTemplateRepository.findAll().spliterator(), false)
                .map(ContractTemplateDto::of)
                .collect(Collectors.toList());
    }

    public ContractTemplateDto save(ContractTemplateDto dto) {
        ContractTemplate entity = dto.toEntity();
        contractTemplateRepository.save(entity);
        return ContractTemplateDto.of(entity);
    }
}
