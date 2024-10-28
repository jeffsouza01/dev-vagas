package com.porto.dev_vagas.modules.company.controllers;

import com.porto.dev_vagas.modules.company.dtos.AuthCompanyDTO;
import com.porto.dev_vagas.modules.company.useCases.AuthCompanyUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.AuthenticationException;

@RestController
@RequestMapping("/auth")
public class AuthCompanyController {

    @Autowired
    private AuthCompanyUseCase authUseCase;

    @PostMapping("/company")
    public ResponseEntity<Object> authenticate(@RequestBody AuthCompanyDTO authCompanyDTO) throws AuthenticationException {
        try {
            var token = authUseCase.execute(authCompanyDTO);
            return ResponseEntity.status(HttpStatus.OK).body(token);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }


    }
}
