package com.porto.dev_vagas.modules.candidate.controllers;

import com.porto.dev_vagas.modules.candidate.Candidate;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @PostMapping("/new")
    public ResponseEntity create(@Valid @RequestBody Candidate candidate) {
        System.out.println(candidate.getName());
        System.out.println(candidate.getEmail());
        System.out.println(candidate.getUsername());



        return null;
    }
}
