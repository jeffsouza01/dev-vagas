package com.porto.dev_vagas.modules.candidate.controllers;

import com.porto.dev_vagas.modules.candidate.Candidate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @PostMapping("/new")
    public ResponseEntity create(@RequestBody Candidate candidate) {
        return null;
    }
}
