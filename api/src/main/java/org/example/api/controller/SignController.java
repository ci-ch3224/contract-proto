package org.example.api.controller;

import java.util.List;

import org.example.api.dto.SignDto;

import org.example.api.service.SignService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/signs")
public class SignController {

    private final SignService signService;

    public SignController(SignService signService) { this.signService = signService; }

    @GetMapping
    public List<SignDto> getList() {
        return signService.getSigns();
    }

    @PostMapping
    public SignDto save(@RequestBody SignDto dto) {
        return signService.save(dto);
    }
}
