package com.porto.dev_vagas.modules.candidate.useCases;

import com.porto.dev_vagas.exceptions.UserFoundException;
import com.porto.dev_vagas.modules.candidate.Candidate;
import com.porto.dev_vagas.modules.candidate.repositories.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateCandidateUseCase {

    @Autowired
    private CandidateRepository repository;

    public Candidate execute(Candidate candidate) {

        this.repository
                .findByUsernameOrEmail(candidate.getUsername(), candidate.getEmail())
                .ifPresent(user -> {
                    throw new UserFoundException();
                });

        return this.repository.save(candidate);
    }
}
