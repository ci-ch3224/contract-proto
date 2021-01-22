package org.example.api.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.example.api.domain.ContractTemplate;
import org.example.api.dto.ContractTemplateDto;
import org.example.api.repository.ContractTemplateRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
    
    public Page<ContractTemplateDto> getContractTemplates(Pageable pageable) {
        return contractTemplateRepository.findAll(pageable)
        			.map(ContractTemplateDto::of);
    }

    public ContractTemplateDto save(ContractTemplateDto dto) {
        ContractTemplate entity = dto.toEntity();
        contractTemplateRepository.save(entity);
        return ContractTemplateDto.of(entity);
    }
}
