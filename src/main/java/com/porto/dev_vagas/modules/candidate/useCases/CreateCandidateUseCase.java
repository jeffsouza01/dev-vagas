package com.porto.dev_vagas.modules.candidate.useCases;

import com.porto.dev_vagas.exceptions.UserFoundException;
import com.porto.dev_vagas.modules.candidate.Candidate;
import com.porto.dev_vagas.modules.candidate.repositories.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreateCandidateUseCase {

    @Autowired
    private CandidateRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Candidate execute(Candidate candidate) {

        this.repository
                .findByUsernameOrEmail(candidate.getUsername(), candidate.getEmail())
                .ifPresent(user -> {
                    throw new UserFoundException();
                });

        var pass = passwordEncoder.encode(candidate.getPassword());

        candidate.setPassword(pass);
        return this.repository.save(candidate);
    }
}
