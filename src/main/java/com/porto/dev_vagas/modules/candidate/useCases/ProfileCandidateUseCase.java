package com.porto.dev_vagas.modules.candidate.useCases;

import com.porto.dev_vagas.modules.candidate.dtos.ProfileCandidateResponseDTO;
import com.porto.dev_vagas.modules.candidate.repositories.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProfileCandidateUseCase {

    @Autowired
    private CandidateRepository repository;

    public ProfileCandidateResponseDTO execute(UUID idCandidate){

        var candidate = repository.findById(idCandidate)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found"));


        return ProfileCandidateResponseDTO.builder()
                .name(candidate.getName())
                .email(candidate.getEmail())
                .username(candidate.getUsername())
                .description(candidate.getDescription())
                .build();
    }
}
