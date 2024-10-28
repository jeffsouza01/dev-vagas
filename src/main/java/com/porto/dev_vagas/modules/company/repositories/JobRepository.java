package com.porto.dev_vagas.modules.company.repositories;

import com.porto.dev_vagas.modules.company.entities.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface JobRepository extends JpaRepository<Job, UUID> {

    List<Job> findByDescriptionContainingIgnoreCase(String filter);
}
