package com.porto.dev_vagas.modules.candidate.dtos;

public record AuthCandidateRequestDTO(
        String email,
        String password
) {
}
