package org.example.api.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.example.api.dto.CompanyDto;
import org.example.api.repository.CompanyRepository;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {
    private final CompanyRepository companyRepository;
	
	public CompanyService(CompanyRepository companyRepository) {
		this.companyRepository = companyRepository;
	}

	public List<CompanyDto> getContracts() {
        return StreamSupport.stream(companyRepository.findAll().spliterator(), false)
                .map(CompanyDto::of)
                .collect(Collectors.toList());
    }
}
