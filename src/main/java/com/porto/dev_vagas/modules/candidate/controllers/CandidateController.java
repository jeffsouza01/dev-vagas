package com.porto.dev_vagas.modules.candidate.controllers;

import com.porto.dev_vagas.modules.candidate.Candidate;
import com.porto.dev_vagas.modules.candidate.useCases.CreateCandidateUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired
    private CreateCandidateUseCase candidateUseCase;

    @PostMapping("/new")
    public ResponseEntity<Object> create(@Valid @RequestBody Candidate candidate) {

        try {
            var result = this.candidateUseCase.execute(candidate);
            return ResponseEntity.status(HttpStatus.CREATED).body(result);

        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }
}
