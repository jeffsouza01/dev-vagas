package com.porto.dev_vagas.modules.company.controllers;


import com.porto.dev_vagas.modules.company.dtos.CreateJobDTO;
import com.porto.dev_vagas.modules.company.entities.Job;
import com.porto.dev_vagas.modules.company.useCases.CreateJobUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/company/job")
public class JobController {

    @Autowired
    private CreateJobUseCase jobUseCase;

    @PostMapping("/create")
    @PreAuthorize("hasRole('COMPANY')")
    public ResponseEntity<Object> create(@Valid @RequestBody CreateJobDTO jobDTO,
                                         HttpServletRequest request) {

        var companyId = request.getAttribute("id_company");
//        job.setIdCompany(UUID.fromString(companyId.toString()));

        try {
            var job = Job.builder()
                    .title(jobDTO.title())
                    .description(jobDTO.description())
                    .level(jobDTO.level())
                    .benefits(jobDTO.benefits())
                    .idCompany(UUID.fromString(companyId.toString()))
                    .build();

            jobUseCase.execute(job);

            return ResponseEntity.status(HttpStatus.CREATED).body(job);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
