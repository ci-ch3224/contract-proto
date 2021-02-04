package org.example.api.service;

import org.example.api.domain.Contract;
import org.example.api.dto.ContractDto;
import org.example.api.repository.ContractRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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

    public Page<ContractDto> getContracts(Pageable pageable) {
        return contractRepository.findAll(pageable)
    			.map(ContractDto::of);
    }

    public ContractDto save(ContractDto dto) {
        Contract entity = dto.toEntity();
        // 갑, 을, 계약일 넣기
        contractRepository.save(entity);
        return ContractDto.of(entity);
    }
}
