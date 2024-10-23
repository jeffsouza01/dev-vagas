package com.porto.dev_vagas.modules.candidate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, UUID> {

    Candidate findByEmail(String email);
    Candidate findByUsernameOrEmail(String username, String email);
}
