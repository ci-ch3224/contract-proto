package org.example.api.service;

import org.example.api.domain.Contract;
import org.example.api.dto.ContractDto;
import org.example.api.repository.ContractRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ContractService {
    private final ContractRepository contractRepository;

    public ContractService(ContractRepository contractRepository) {
        this.contractRepository = contractRepository;
    }

    public ContractDto getContract(Long id) {
        return contractRepository.findById(id)
                .map(ContractDto::of)
                .orElseThrow(RuntimeException::new);
    }

    public List<ContractDto> getContracts() {
        return StreamSupport.stream(contractRepository.findAll().spliterator(), false)
                .map(ContractDto::of)
                .collect(Collectors.toList());
    }

    public ContractDto save(ContractDto dto) {
        Contract entity = dto.toEntity();
        contractRepository.save(entity);
        return ContractDto.of(entity);
    }
}
