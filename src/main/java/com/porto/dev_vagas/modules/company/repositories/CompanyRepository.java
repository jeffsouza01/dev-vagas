package com.porto.dev_vagas.modules.company.repositories;

import com.porto.dev_vagas.modules.company.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CompanyRepository extends JpaRepository<Company, UUID> {

    Optional<Company> findByUsernameOrEmail(String username, String email);

    Optional<Company> findByEmail(String email);
}
