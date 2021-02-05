package org.example.api.service;

import org.example.api.domain.Contract;
import org.example.api.domain.Sign;
import org.example.api.dto.ContractDto;
import org.example.api.dto.SignDto;
import org.example.api.repository.SignRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class SignService {
    private final SignRepository signRepository;

    public SignService(SignRepository signRepository) {
        this.signRepository = signRepository;
    }

    public List<SignDto> getSigns() {
        return StreamSupport.stream(signRepository.findAll().spliterator(), false)
                .map(SignDto::of)
                .collect(Collectors.toList());
    }

    public SignDto save(SignDto dto) {
        Sign entity = dto.toEntity();
        signRepository.save(entity);
        return SignDto.of(entity);
    }
}
