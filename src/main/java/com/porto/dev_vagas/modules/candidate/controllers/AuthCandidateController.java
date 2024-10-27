package com.porto.dev_vagas.modules.candidate.controllers;

import com.porto.dev_vagas.modules.candidate.dtos.AuthCandidateRequestDTO;
import com.porto.dev_vagas.modules.candidate.useCases.AuthCandidateUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.AuthenticationException;

@RestController
@RequestMapping("/candidate")
public class AuthCandidateController {

    @Autowired
    private AuthCandidateUseCase authUseCase;


    @PostMapping("/auth")
    public ResponseEntity<Object> auth(@RequestBody AuthCandidateRequestDTO authDTO) throws AuthenticationException {

        try {
            var authCandidate = authUseCase.execute(authDTO);

            return ResponseEntity.status(HttpStatus.ACCEPTED).body(authCandidate);
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}
