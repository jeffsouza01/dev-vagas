package com.porto.dev_vagas.modules.company.useCases;

import com.porto.dev_vagas.exceptions.UserFoundException;
import com.porto.dev_vagas.modules.company.entities.Company;
import com.porto.dev_vagas.modules.company.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Service;

@Service
public class CreateCompanyUseCase {

    @Autowired
    private CompanyRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    public Company execute(Company company) {

        this.repository.findByUsernameOrEmail(company.getUsername(), company.getEmail())
                .ifPresent(company1 -> {
                    throw new UserFoundException();
                });

        var passEncoder = encoder.encode(company.getPassword());

        company.setPassword(passEncoder);

        return this.repository.save(company);
    }
}
