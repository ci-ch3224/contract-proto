package org.example.api.repository;

import org.example.api.domain.Contract;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ContractRepository extends PagingAndSortingRepository<Contract, Long> {
}
