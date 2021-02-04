package org.example.api.controller;

import java.util.List;

import org.example.api.dto.CompanyDto;

import org.example.api.service.CompanyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    
	private final CompanyService companyService;
	
	public CompanyController(CompanyService companyService) {
		this.companyService = companyService;
	}

	@GetMapping
    public List<CompanyDto> getList() {
        return companyService.getContracts();
    }
}
