package org.example.api.repository;


import org.example.api.domain.Sign;
import org.springframework.data.repository.CrudRepository;

public interface SignRepository extends CrudRepository <Sign, Long> {
}
