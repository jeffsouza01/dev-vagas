package com.porto.dev_vagas.modules.company.controllers;


import com.porto.dev_vagas.modules.company.entities.Company;
import com.porto.dev_vagas.modules.company.useCases.CreateCompanyUseCase;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CreateCompanyUseCase companyUseCase;

    @PostMapping("/create")
    public ResponseEntity<Object> create(@Valid @RequestBody Company company) {

        try {
            var result = companyUseCase.execute(company);

            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());

        }

    }
}
