package com.porto.dev_vagas.modules.candidate.repositories;

import com.porto.dev_vagas.modules.candidate.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, UUID> {

    Optional<Candidate> findByEmail(String email);
    Optional<Candidate> findByUsernameOrEmail(String username, String email);
}
