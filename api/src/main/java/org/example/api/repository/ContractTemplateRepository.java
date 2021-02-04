package org.example.api.repository;

import org.example.api.domain.ContractTemplate;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ContractTemplateRepository extends PagingAndSortingRepository<ContractTemplate, Long> {
}
